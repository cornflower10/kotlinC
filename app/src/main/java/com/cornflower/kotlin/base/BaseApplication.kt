package com.cornflower.kotlin.base

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by xiejingbao on 2019/5/22.
 */
open class BaseApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
    }
     companion object {
         open var context:Context by Delegates.notNull()
    }
}