package com.kele.androidstudio.kotlinimage.utils

/*
* RxPermissions http://blog.csdn.net/u013553529/article/details/68948971
*/

class PermissionUtils() {

    companion object {

    }


    //RxPermissions申请权限
//    RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE).subscribe({ t: Boolean ->
//        if (t) {
//
//        } else {
//
//        }
//    })
//    RxPermissions(this).requestEach(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE).subscribe(Consumer<Permission> { t: Permission ->
//      分别查看权限是否通过
//      if (t.name.equals(Manifest.permission.CAMERA)) {
//
//        } else {
//
//        }
//    })

    //普通方法
//        if (PermissionChecker.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
//                || PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
//                || PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED) {
//            //申请多个权限
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE), 5)
//        } else {
//            UIManager.toMain(this)
//            finish()
//        }

//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (grantResults.size != 1 || grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            UIManager.toMain(this)
//            finish()
//        } else {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        }
//
//    }
}