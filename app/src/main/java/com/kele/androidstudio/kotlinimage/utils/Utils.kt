package com.kele.androidstudio.kotlinimage.utils

import android.text.TextUtils
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

class Utils {

    companion object {
        /**
         * 获取进程号对应的进程名
         * @param pid 进程号
         * @return 进程名
         */
        fun getProcessName(pid: Int): String? {
            var reader: BufferedReader? = null
            try {
                reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
                var processName = reader!!.readLine()
                if (!TextUtils.isEmpty(processName)) {
                    processName = processName.trim({ it <= ' ' })
                }
                return processName
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            } finally {
                try {
                    if (reader != null) {
                        reader!!.close()
                    }
                } catch (exception: IOException) {
                    exception.printStackTrace()
                }
            }
            return null
        }


    }


}
