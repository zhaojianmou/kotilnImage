package com.kele.androidstudio.kotlinimage.ui.presenter

import com.kele.androidstudio.kotlinimage.ui.activity.LoginActivity
import com.kele.androidstudio.kotlinimage.ui.contract.LoginContract
import com.kele.androidstudio.kotlinimage.ui.view.LoginView
import java.lang.ref.SoftReference

class LoginPersenter : LoginContract.Presenter {
    var mView: LoginView? = null;

    constructor(view: LoginView) {
        mView = view;
    }


    override fun login(name: String, pwd: String) {



    }


}
