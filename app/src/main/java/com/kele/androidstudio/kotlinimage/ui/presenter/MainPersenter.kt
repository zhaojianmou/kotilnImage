package com.kele.androidstudio.kotlinimage.ui.presenter

import com.kele.androidstudio.kotlinimage.ui.contract.SplashContract

class MainPersenter<T>(t: T) : BasePresenterImpl<T>(t), SplashContract.Presenter {


    override fun getInitData() {

    }


}
