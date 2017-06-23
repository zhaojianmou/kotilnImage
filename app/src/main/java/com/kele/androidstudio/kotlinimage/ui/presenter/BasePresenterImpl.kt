package com.kele.androidstudio.kotlinimage.ui.presenter


abstract class BasePresenterImpl<T> {
    var mView: T? = null

    constructor(view: T) {
        mView = view
        getInitData()
    }

    abstract fun getInitData()


}
