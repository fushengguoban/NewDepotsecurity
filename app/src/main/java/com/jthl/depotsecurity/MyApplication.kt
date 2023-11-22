package com.jthl.depotsecurity

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 * @author wanglei
 * @date 2023/11/13 22:53
 * @Description:
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MMKV.initialize(this)

    }
}