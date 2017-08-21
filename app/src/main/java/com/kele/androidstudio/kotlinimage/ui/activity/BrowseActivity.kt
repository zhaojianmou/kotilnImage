package com.kele.androidstudio.kotlinimage.ui.activity

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.constant.UIConstant
import com.kele.androidstudio.kotlinimage.ui.base.UIManager
import kotlinx.android.synthetic.main.activity_photobrowse.*
import kotlinx.android.synthetic.main.title_bar_main.*

class BrowseActivity : BaseActivity(), View.OnClickListener {
    val TAG = "BrowseActivity"
    var path = ""


    override fun getLayoutId(): Int {
        return R.layout.activity_photobrowse
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (intent != null && intent.extras != null) {
            path = intent.extras.getString(UIConstant.IMAGE_PATH)
        }

        right.setOnClickListener(this)

        titleTv.setText(R.string.browse_show)
        rightTv.setText(R.string.bar_editor)

    }

    override fun initData() {
        //test HttpConstant.IMAGE
        imageView.setPhotoUri(Uri.parse(path))

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.right -> {
                var bundler = Bundle()
                bundler.putString(UIConstant.IMAGE_PATH, path)
                UIManager.toFilter(this, bundler)

            }

        }
    }


}
