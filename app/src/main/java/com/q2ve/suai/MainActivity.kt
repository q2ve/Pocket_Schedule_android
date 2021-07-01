package com.q2ve.suai

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.ui.RootNavigation
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import io.realm.Realm


class MainActivity : AppCompatActivity() {

    private val rootNavigation = RootNavigation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting link to activity in fragment replacer
        FragmentReplacer.activityLink = this
        //Setting link to activity in fragment replacer

        //Realm
        Realm.init(this)
        //Realm

        //View and window setting
        setContentView(R.layout.main_activity)
        this.window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or
                                           View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        //View and window setting

        //Adding root navigation fragment on the screen
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_activity_frame, rootNavigation)
        transaction.addToBackStack(null)
        transaction.commit()
        //Adding root navigation fragment on the screen
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //VK
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                Log.d("Tag", token.accessToken)
            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
        //VK
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //Toast.makeText(applicationContext, "Пошел нахуй!", Toast.LENGTH_SHORT).show()
    }
}