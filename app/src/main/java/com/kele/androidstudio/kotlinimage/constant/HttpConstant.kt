package com.kele.androidstudio.kotlinimage.constant

class HttpConstant {
    companion object {
        val ROOT = "http://xayuetu.cn/shateimage/"
        val HTTP = ROOT + "app/ui/"

        // *************************************** 页面 ***************************************
        val HTTP_IMAGE = HTTP + "image.php"

        val QINIU = "http://ostcb2rtk.bkt.clouddn.com";
        val IMAGE = QINIU + "/liu.jpeg"
        val IMAGE_TEST = QINIU + "/liu.jpeg"
//                "http://xayuetu.cn/shareimage/image/liu.jpeg"



    }
}