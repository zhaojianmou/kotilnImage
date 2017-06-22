package com.kele.androidstudio.kotlinimage.ui.contract

import com.kele.androidstudio.kotlinimage.ui.presenter.BasePresenter
import com.kele.androidstudio.kotlinimage.ui.view.BaseView

interface MainContract {

    interface Presenter : BasePresenter {

        fun login(name: String, pwd: String)

    }

    interface View : BaseView {


    }


}
