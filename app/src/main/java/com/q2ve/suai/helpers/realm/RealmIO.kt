package com.q2ve.suai.helpers.realm

/**
 * Created by Denis Shishkin on 11.06.2021.
 * qwq2eq@gmail.com
 */

import android.util.Log
import com.q2ve.suai.helpers.realm.objects.IndexerItem
import com.q2ve.suai.helpers.realm.objects.IndexerItemType
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.Sort
import kotlin.reflect.KMutableProperty

class RealmIO {
	private val realmName: String = "SUAI_database"
	private val config = RealmConfiguration.Builder()
		.name(realmName)
		.allowQueriesOnUiThread(true)   //Why are you booing me?
		.allowWritesOnUiThread(true)    //We can read from Realm synchronously, cause it fast!
		.build()                        //No need to pay attention to "Writes" permission. It's ok

	//Example function. I'll eventually delete it sometime
	private fun example (parent: RealmInterface, items: List<RealmObject>) {
		val realm = Realm.getInstance(config)
		realm.executeTransactionAsync (
			Realm.Transaction {r: Realm ->
				r.insertOrUpdate(items)
			},
			Realm.Transaction.OnSuccess {
				Log.d("Realm insertOrUpdate", items.toString())
				parent.realmCallback()
			},
			Realm.Transaction.OnError {
				parent.realmCallback(null, true, it)
				Log.e("!E Realm insertOrUpdate", it.toString())
			}
		)
		realm.close()
	}

	//This function is needed to index and add items we got from the server to the local database
	fun <T: RealmObject> insertOrUpdate(
		parent: RealmInterface,
		type: IndexerItemType,
		inputObjects: List<T>)
	{
		val realm = Realm.getInstance(config)
		val name = type.toString()
		var output: List<T> = emptyList()
		realm.executeTransactionAsync(
			{ r: Realm ->
				var count = r.where(IndexerItem::class.java).equalTo("name", name).findAll().size
				inputObjects.forEach { it ->
					//Indexer items needs to get the objects from DB
					// in the correct order they came from the server
					val indexerItem = IndexerItem()
					indexerItem.name = name
					indexerItem.index = count
					val outputObject = r.copyToRealmOrUpdate(it)
					val field = type.getField()
					val properties = indexerItem::class.members
					//A Great Reflexive Magic that finds right field in indexer item
					// and fills it with the object from server.
					//It's necessary for this code to work with objects of any classes
					// that i might add in the future
					val property = properties.find { it.name == field }
					if (property is KMutableProperty<*>) {
						property.setter.call(indexerItem, outputObject)
					}
					//Giving ID to indexer item,
					// getting it from an input object's ID with "indexer_" prefix adding
					val id = it::class.members.find { it.name == "_id" }
					if (id is KMutableProperty<*>) {
						indexerItem._id = "indexer_" + id.getter.call(it).toString()
					}
					r.insertOrUpdate(indexerItem)
					count++
				}
				//Sorting objects from DB with indexer items (also from DB)
				val indexerSortedList = r.where(IndexerItem::class.java)
										.equalTo("name", name)
										.sort("index", Sort.ASCENDING)
										.findAll()
				indexerSortedList.forEach { it ->
					val properties = IndexerItem::class.members
					val property = properties.find { it.name == type.getField() }
					if (property is KMutableProperty<*>) {
						output += property.getter.call(it) as T
					}
				}
				//Copying objects from DB. Now they're not related to DB
				output = r.copyFromRealm(output)
				Log.d("Realm output", output.toString())
			}, {
				parent.realmCallback(output)
				Log.d("Realm insertOrUpdate", "Successful")
			}, {
				parent.realmCallback(isError = true, throwable = it)
				Log.e("!E Realm insertOrUpdate", it.toString())
			}
		)
		realm.close()
	}
}