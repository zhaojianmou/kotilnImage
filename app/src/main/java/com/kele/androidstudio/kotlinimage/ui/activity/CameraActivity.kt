package com.kele.androidstudio.kotlinimage.ui.activity

import android.graphics.Point
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.FrameLayout
import com.kele.androidstudio.kotlinimage.R
import com.kele.androidstudio.kotlinimage.base.BaseActivity
import com.seu.magicfilter.MagicEngine
import com.seu.magicfilter.filter.helper.MagicFilterType
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CameraActivity : BaseActivity(), View.OnClickListener {


    var magicEngine: MagicEngine? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_camera
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        play.setOnClickListener(this)

        magicEngine = MagicEngine.Builder().build(magicCameraView)


        val screenSize = Point()
        windowManager.defaultDisplay.getSize(screenSize)
        val params = magicCameraView.layoutParams as FrameLayout.LayoutParams
        params.width = screenSize.x
        params.height = screenSize.x * 4 / 3
        magicCameraView.layoutParams = params

    }


    override fun onClick(v: View?) {
//        magicEngine!!.savePicture(getOutputMediaFile(), null)
        (magicEngine as MagicEngine?)!!.setFilter(MagicFilterType.AMARO)

    }

    fun getOutputMediaFile(): File? {
        val mediaStorageDir = File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MagicCamera")
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINESE).format(Date())
        val mediaFile = File(mediaStorageDir.path + File.separator +
                "IMG_" + timeStamp + ".jpg")

        return mediaFile
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
