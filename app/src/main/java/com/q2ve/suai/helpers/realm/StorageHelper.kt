package com.q2ve.suai.helpers.realm

import android.util.Log
import com.q2ve.suai.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject

interface UniqueImportSource {
	val id: String
}

interface ImportableObject<ImportSource> {
	val id: String
	fun setup(source: ImportSource, inTransaction: Realm)
}

object StorageHelper {

	fun realmInsance(): Realm {
		val configuration = RealmConfiguration.Builder()
			.name(Constants.realmDatabaseFileName)
			.allowQueriesOnUiThread(true)   //Why are you booing me?
			.allowWritesOnUiThread(true)    //We can read from Realm synchronously, cause it fast!
			.build()

		return Realm.getInstance(configuration)
	}

	inline fun <reified Object, ImportSource> storeObject(
		from: ImportSource,
		crossinline completion: (result: Object?) -> Unit,
		crossinline errorHandler: (Throwable) -> Unit
	)
	where Object : RealmObject,
	      Object: ImportableObject<ImportSource>,
	      ImportSource: UniqueImportSource
	{
		val realm = realmInsance()
		realm.executeTransactionAsync(
			{r: Realm ->
				val imported = r.importObject<Object, ImportSource>(from)
				val fetched = r.where(Object::class.java)
					.equalTo("id", imported.id)
					.findFirst()
				completion(fetched)
			},
			{
				realm.close()
			},
			{
				realm.close()
				errorHandler(it)
			}
		)
	}
}


inline fun <reified Object, ImportSource> Realm.importObject(from: ImportSource ): Object
		where Object : RealmObject,
		      Object: ImportableObject<ImportSource>,
		      ImportSource: UniqueImportSource
{
	val found = this.where(Object::class.java)
		.equalTo("id", from.id)
		.findFirst()
	if( found != null ) {
		found.setup(from, this)
		return found
	} else {
		val created = this.createObject(Object::class.java)
		created.setup(from,this)
		return created
	}
}