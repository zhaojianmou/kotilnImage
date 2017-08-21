package com.kele.androidstudio.kotlinimage.ui.view

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.jack.commonlibrary.view.andwidght.PopupWindow7Utils
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.ui.activity.AboutActivity
import com.kele.androidstudio.kotlinimage.ui.base.UIManager
import com.kele.androidstudio.kotlinimage.ui.contract.SplashContract
import com.kele.androidstudio.kotlinimage.ui.fragment.ShowImageFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title_bar_main.*

class MainView<T> : BaseViewImpl<T>, SplashContract.View, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    val SHOWIMAGE_FG = 1
    val SET_FG = 2

    var navigatonView: NavigationView? = null
    var drawerLayout: DrawerLayout? = null

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
        var beginTransaction = getActivity()!!.supportFragmentManager.beginTransaction()
        when (option) {
            SHOWIMAGE_FG -> {
                var showImageFragment = ShowImageFragment.getInstance()
                beginTransaction.replace(R.id.mainpager, showImageFragment)
            }
        }
        beginTransaction.commitNowAllowingStateLoss()
    }

    fun optionBrowseType(v: View) {
        var view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_browse, null)
        var pw = PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        pw.setTouchable(true)
        pw.setOutsideTouchable(true)

        var show = view.findViewById(R.id.show)
        var location = view.findViewById(R.id.location)

        show.setOnClickListener {
            setRightTV(UIConstant.BROWSE_SHOW)
            pw.dismiss()
        }
        location.setOnClickListener {
            setRightTV(UIConstant.BROWSE_LOCATION)
            pw.dismiss()
        }
        pw.showAsDropDown(v)
//        PopupWindow7Utils.showAtLocation(pw, v)
    }

    fun setRightTV(text: String) {
        getActivity()!!.rightTv.setText(text)
    }


}