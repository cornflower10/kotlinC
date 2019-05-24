package com.cornflower.kotlin

import android.app.Application
import android.content.Context
import com.hjq.toast.ToastUtils
import kotlin.properties.Delegates

/**
 * Created by xiejingbao on 2019/5/22.
 */
class App : Application() {
   companion object {
       open var context: Context by Delegates.notNull()
   }

    override fun onCreate() {
        super.onCreate()
         context = this
        ToastUtils.init(this)
    }
}