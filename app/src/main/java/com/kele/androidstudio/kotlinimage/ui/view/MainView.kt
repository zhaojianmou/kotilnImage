package com.kele.androidstudio.kotlinimage.ui.view

import android.app.ActivityOptions
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import android.view.View
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.ui.activity.AboutActivity
import com.kele.androidstudio.kotlinimage.ui.contract.SplashContract
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_bar_main.*

class MainView<T> : BaseViewImpl<T>, SplashContract.View, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    var navigatonView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null


    constructor(t: T) {
        init(t)
    }

    override fun titleBar() {
        getActivity()!!.leftIv.setImageResource(R.mipmap.ic_menu_black_48dp)

        getActivity()!!.left.setOnClickListener(this)

        getActivity()!!.titleTv.setText(R.string.app_name)

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


    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.left -> drawerLayout!!.openDrawer(GravityCompat.START)


        }


    }


}