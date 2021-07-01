package com.q2ve.suai.ui.loginNavigation.loginFragments

import android.app.Activity
import androidx.fragment.app.Fragment
import com.q2ve.suai.R
import com.q2ve.suai.helpers.FragmentReplacer
import com.q2ve.suai.helpers.contentGetter.ContentGetter
import com.q2ve.suai.helpers.contentGetter.ContentGetterInterface
import com.q2ve.suai.helpers.realm.objects.RealmIdNameInterface
import com.q2ve.suai.ui.bottomMenu.BottomMenuInterface
import com.q2ve.suai.ui.bottomMenu.BottomMenuPresenter
import com.q2ve.suai.ui.bottomMenu.BottomMenuView
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerInterface
import com.q2ve.suai.ui.bottomMenu.selector.RecyclerSelectorView
import com.q2ve.suai.ui.loginNavigation.LoginNavigationInterface
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope

interface LoginScreenFirstPresenterInterface {
    fun enterButtonPressed (universityName: String)
    fun vkButtonPressed (activity: Activity)
    fun backButtonPressed ()
    fun universitySelectorPressed()
}

class LoginFirstPresenter(private val parent: LoginNavigationInterface):
    Fragment(),
    LoginScreenFirstPresenterInterface,
    ContentGetterInterface,
    RecyclerInterface,
    BottomMenuInterface
{

    lateinit var view: LoginScreenFirstViewInterface
    private val bottomMenuView = BottomMenuView("Выбор хуйни")
    private val bottomMenuPresenter = BottomMenuPresenter(bottomMenuView, this)
    private var universityList: List<RealmIdNameInterface>? = null
    private var isBottomMenuOpened = false

    init {
    	getUniversities()
    }

    private fun getUniversities () {
        ContentGetter(this).getUniversities()
    }

    private fun placeRecycler () {
        val recycler = RecyclerSelectorView(this, universityList!!)
        FragmentReplacer.addFragment(R.id.bottom_menu_recycler_container, recycler)
    }

    override fun enterButtonPressed(universityName: String) {
        view.makeErrorMessage(true, 1)
        if (universityName.length<2) {
            view.makeErrorMessage(false, R.string.error_no_university_selected)
        }
        else {
            parent.goToSecondScreen(universityName)
        }
    }

    override fun vkButtonPressed(activity: Activity) {
        VK.login(activity, arrayListOf(VKScope.WALL, VKScope.OFFLINE))
    }

    override fun backButtonPressed() {
        TODO("Not yet implemented")
    }

    override fun universitySelectorPressed() {
        bottomMenuView.presenter = bottomMenuPresenter
        FragmentReplacer.addFragment(R.id.login_navigation, bottomMenuView)
        isBottomMenuOpened = true

        if (universityList !== null) {
            placeRecycler()
        }
    }

    override fun contentGetterCallback(
        objects: List<Any>?,
        isError: Boolean,
        errorMessage: String?
    ) {
        if (isError) {
            TODO()
        } else {
            universityList = objects as List<RealmIdNameInterface>
            if (isBottomMenuOpened) {
                placeRecycler()
            }
        }
    }

    override fun onRecyclerItemClicked(realmObject: RealmIdNameInterface) {
        view.bindNewUniversityName(realmObject.getTheName())
        bottomMenuView.exitAnimation()
    }

    override fun bottomMenuClosed() {
        isBottomMenuOpened = false
    }
}