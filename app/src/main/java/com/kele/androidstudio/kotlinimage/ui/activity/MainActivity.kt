package com.kele.androidstudio.kotlinimage.ui.activity

import android.content.Intent
import android.os.Bundle
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.ui.presenter.MainPersenter
import com.kele.androidstudio.kotlinimage.ui.view.MainView

class MainActivity : BaseActivity() {
    val TAG = "MainActivity"


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        var view = MainView<MainActivity>(this)
        var presenter = MainPersenter<MainView<MainActivity>>(view)
        view.setPersenter(presenter)
    }

    override fun initData() {
//        MediaContent.sendGetContent(this)

//        val list = ImageManager.getImageProvide().images
//        for (path: String in list) {
//            Log.e(TAG, "" + path)
//        }
//        imageView.setPhotoUri(Uri.parse("file://" + list[5]))
//        imageView.setPhotoUri(Uri.parse(HttpConstant.IMAGE))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == ImageManager.SELECT_PIC && resultCode == Activity.RESULT_OK) {
//            Log.e(TAG, "" + MediaContent.getPathContent(data))
//        }
    }


}
