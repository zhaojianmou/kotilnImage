package com.kele.androidstudio.kotlinimage.ui.view

import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.kele.androidstudio.kotlinimage.base.BaseFragment
import com.kele.androidstudio.kotlinimage.ui.presenter.BasePresenter
import java.lang.ref.SoftReference


abstract class BaseViewImpl<T> {

    var mActivity: SoftReference<T>? = null
    var mFragment: SoftReference<T>? = null
    var mPersenter: BasePresenter? = null

    constructor(t: T) {
        setActivity(t)
    }


    fun setActivity(t: T) {
        if (t as? BaseActivity != null) {
            mActivity = SoftReference(t)
        } else if (t as? BaseFragment != null) {
            mFragment = SoftReference(t)
        }
        titleBar()
        initView()
    }

    fun setPersenter(persenter: BasePresenter) {
        mPersenter = persenter
        initUpdateView()
    }


    abstract fun titleBar()

    abstract fun initView()

    abstract fun initUpdateView()

    abstract fun UpdateView(any: Any?)


    fun getActivity(): BaseActivity? {
        if (mActivity != null && mActivity!!.get() != null) {
            return mActivity!!.get() as BaseActivity
        }
        if (mFragment != null && mFragment!!.get() != null) {
            return (mFragment!!.get() as BaseFragment).activity as BaseActivity
        }
        return null
    }

    fun getFragment(): BaseFragment? {
        if (mFragment != null && mFragment!!.get() != null) {
            return mFragment!!.get() as BaseFragment
        }
        return null
    }


}
