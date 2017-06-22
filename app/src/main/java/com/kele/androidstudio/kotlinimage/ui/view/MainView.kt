package com.kele.androidstudio.kotlinimage.ui.view

import android.app.ActivityOptions
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.ui.activity.AboutActivity
import com.kele.androidstudio.kotlinimage.ui.contract.SplashContract
import kotlinx.android.synthetic.main.activity_main.*

class MainView<T>(t: T) : BaseViewImpl<T>(t), SplashContract.View, NavigationView.OnNavigationItemSelectedListener {


    var navigatonView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null

    override fun titleBar() {


    }


    override fun initView() {
        navigatonView = getActivity()!!.navigatonView as NavigationView
        drawerLayout = getActivity()!!.drawerLayout as DrawerLayout


        navigatonView!!.setNavigationItemSelectedListener(this)
        navigatonView!!.itemTextColor = getActivity()!!.resources.getColorStateList(R.drawable.navigation_menu_item_color)
        navigatonView!!.setBackgroundResource(R.color.bg_color_685562)
    }

    override fun initUpdateView() {

    }

    override fun UpdateView(any: Any?) {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout!!.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.mHomeMenu -> {
            }
            R.id.mSettingsMenu -> {
            }
            R.id.mAboutMenu -> {
                getActivity()!!.startActivity(Intent(getActivity(), AboutActivity::class.java), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle())
            }

        }

        return true
    }


}