package com.kele.androidstudio.kotlinimage.ui.view

import android.content.Intent
import android.view.View
import android.widget.Toast
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.ui.activity.LoginActivity
import com.kele.androidstudio.kotlinimage.ui.activity.MainActivity
import com.kele.androidstudio.kotlinimage.ui.contract.LoginContract
import com.kele.androidstudio.kotlinimage.ui.presenter.LoginPersenter
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.ref.SoftReference

open class LoginView : BaseView, LoginContract.View, View.OnClickListener {
    val url = "www.baidu.com";
    var mActivity: SoftReference<LoginActivity>? = null;
    var mPersenter: LoginPersenter? = null;


    constructor(activity: LoginActivity) {
        mActivity = SoftReference(activity);
        initView();
    }

    fun setPersenter(persenter: LoginPersenter) {
        mPersenter = persenter;
    }

    fun initView() {
        var activity: LoginActivity = mActivity!!.get()!!

        activity.login.setOnClickListener(this)

    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.name -> Toast.makeText(mActivity!!.get(), "NAME", Toast.LENGTH_SHORT).show();
            R.id.pwd -> {
                Toast.makeText(mActivity!!.get(), "PWD", Toast.LENGTH_SHORT).show();
            }
            R.id.login -> login()
        }
    }

    fun login() {
        Toast.makeText(mActivity!!.get(), "Login", Toast.LENGTH_SHORT).show()
        mPersenter!!.login(getActivity().name.text.toString().trim(), getActivity().pwd.text.toString().trim())

        startMain()
    }

    fun startMain() {
        var intent: Intent = Intent(getActivity(), MainActivity::class.java)
        getActivity().startActivity(intent)
    }

    fun getActivity(): LoginActivity {
        return mActivity!!.get()!!
    }


}
