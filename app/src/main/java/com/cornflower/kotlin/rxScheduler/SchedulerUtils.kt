package com.cornflower.kotlin.rxScheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by xiejingbao on 2019/5/22.
 */
object SchedulerUtils {
    fun <T> ioToMain() = BaseScheduler<T>(Schedulers.io(),AndroidSchedulers.mainThread())
}