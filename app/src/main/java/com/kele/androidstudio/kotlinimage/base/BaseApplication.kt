package com.kele.androidstudio.kotlinimage.base

import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.facebook.drawee.backends.pipeline.Fresco
import com.jack.commonlibrary.app.AppUtils
import com.kele.androidstudio.kotlinimage.BuildConfig
import com.kele.androidstudio.kotlinimage.base.api.InitApplication
import com.kele.androidstudio.kotlinimage.constant.AppConstant
import com.kele.androidstudio.kotlinimage.image.ImageManager
import com.kele.androidstudio.kotlinimage.utils.constant.ExitApp
import com.kele.androidstudio.kotlinimage.utils.constant.LeakCanaryUtils
import com.tencent.bugly.crashreport.CrashReport
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import com.kele.androidstudio.kotlinimage.utils.Utils


open class BaseApplication : InitApplication() {
    //动画组件

    //http://www.bbtexiao.com/site/free
    //宝宝特效   key:  c4a9c1f2148d92874e9e488b8b20617f


    override fun initAfter() {
        application = this

    }


    override fun initData() {
        performance()
        //加载工具包
        AppUtils.init(this)
        ImageManager.init(this)
        Fresco.initialize(this)
        initBaiduMap()

    }

    override fun initBefore() {


    }

    fun initBaiduMap() {
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(applicationContext)
        // 自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        // 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)
        // OpenLogUtil.setModuleLogEnable(ModuleName.TILE_OVERLAY_MODULE, true)
    }

    override fun performance() {
        LeakCanaryUtils.checkLeakCanary()

        // bugly异常信息统计
        // 设置是否为上报进程
        val strategy = UserStrategy(applicationContext)
        strategy.isUploadProcess = (applicationContext.packageName == null || applicationContext.packageName.equals(Utils.getProcessName(android.os.Process.myPid())))
        CrashReport.initCrashReport(applicationContext, AppConstant.BUGLY_APP_ID, BuildConfig.DEBUG, strategy)
    }


    override fun clearAppMemory() {

    }


    override fun exitApp() {
        ExitApp.exitApp()
    }


}