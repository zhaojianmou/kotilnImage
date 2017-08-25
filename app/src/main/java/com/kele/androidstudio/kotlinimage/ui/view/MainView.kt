package com.kele.androidstudio.kotlinimage.ui.view

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseFragment
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.ui.base.UIManager
import com.kele.androidstudio.kotlinimage.ui.contract.SplashContract
import com.kele.androidstudio.kotlinimage.ui.fragment.SetFragment
import com.kele.androidstudio.kotlinimage.ui.fragment.ShowImageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_bar_main.*
import java.util.*

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class MainView<T> : BaseViewImpl<T>, SplashContract.View, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    val SHOWIMAGE_FG = 1
    val SET_FG = 2

    var navigatonView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null

    val fragmentList = ArrayList<BaseFragment>()


    constructor(t: T) {
        init(t)
    }


    override fun titleBar() {
        getActivity()!!.leftIv.setImageResource(R.mipmap.ic_menu_black_48dp)

        getActivity()!!.left.setOnClickListener(this)
        getActivity()!!.right.setOnClickListener(this)

        getActivity()!!.titleTv.setText(R.string.app_name)
        getActivity()!!.rightTv.setText(R.string.browse_show)

    }


    override fun initView() {
        navigatonView = getActivity()!!.navigatonView as NavigationView
        drawerLayout = getActivity()!!.drawerLayout as DrawerLayout


        navigatonView!!.setNavigationItemSelectedListener(this)
        navigatonView!!.itemTextColor = getActivity()!!.resources.getColorStateList(R.drawable.navigation_menu_item_color)
        navigatonView!!.setBackgroundResource(R.color.bg_color_685562)

        switchFragement(SHOWIMAGE_FG)
    }

    override fun initUpdateView() {

    }

    override fun UpdateView(any: Any?) {

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        drawerLayout!!.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.mHomeMenu -> {
                switchFragement(SHOWIMAGE_FG)
            }
            R.id.mSettingsMenu -> {
                switchFragement(SET_FG)
            }
            R.id.mAboutMenu -> {
                UIManager.toAbout(getActivity()!!)
            }
        }
        return true
    }


    override fun onClick(p0: View?) {

        when (p0!!.id) {
            R.id.left -> drawerLayout!!.openDrawer(GravityCompat.START)
            R.id.right -> optionBrowseType(p0)


        }


    }

    private fun switchFragement(option: Int) {
        var fragmentManager = getActivity()!!.supportFragmentManager
        var beginTransaction = fragmentManager.beginTransaction()

        when (option) {
            SHOWIMAGE_FG -> {
                var fragment = fragmentManager.findFragmentByTag(ShowImageFragment.javaClass.name) as ShowImageFragment?
                if (fragment == null) {
                    fragment = ShowImageFragment.getInstance()
                    fragmentList.add(fragment!!)
                    beginTransaction.add(R.id.mainpager, fragment, ShowImageFragment.javaClass.name)
                }
                showFragment(beginTransaction, fragment)
            }
            SET_FG -> {
                var fragment = fragmentManager.findFragmentByTag(SetFragment.javaClass.name) as SetFragment?
                if (fragment == null) {
                    fragment = SetFragment.getInstance()
                    fragmentList.add(fragment!!)
                    beginTransaction.add(R.id.mainpager, fragment, SetFragment.javaClass.name)
                }
                showFragment(beginTransaction, fragment)
            }
        }
        beginTransaction.commitAllowingStateLoss()
    }


    fun showFragment(beginTransaction: FragmentTransaction, fragment: BaseFragment) {
        if (fragment!!.isHidden) {
            beginTransaction.show(fragment)
        }
        for (fg: BaseFragment in fragmentList) {
            if (fg == fragment) {
                continue
            }
            beginTransaction.hide(fg)
        }
    }


    fun optionBrowseType(v: View) {
        var view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_browse, null)
        var pw = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        pw.setTouchable(true)
        pw.setOutsideTouchable(true)

        var show = view.findViewById(R.id.show)
        var location = view.findViewById(R.id.location)
        var map = view.findViewById(R.id.map)

        show.setOnClickListener {
            setRightTV(UIConstant.BROWSE_SHOW)
            pw.dismiss()
        }
        location.setOnClickListener {
            setRightTV(UIConstant.BROWSE_LOCATION)
            pw.dismiss()
        }
        map.setOnClickListener {
            //            setRightTV(UIConstant.BROWSE_LOCATION)
            var bundler = Bundle()
            bundler.putString(UIConstant.MAP_TYPE, UIConstant.TYPE_LIST)
            UIManager.toMap(getActivity()!!, bundler)
            pw.dismiss()
        }
        pw.showAsDropDown(v)
//        PopupWindow7Utils.showAtLocation(pw, v)
    }

    fun setRightTV(text: String) {
        getActivity()!!.rightTv.setText(text)
    }


}