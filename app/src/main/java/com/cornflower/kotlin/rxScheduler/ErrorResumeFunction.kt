package com.cornflower.kotlin.rxScheduler

import com.cornflower.kotlin.base.CommonResEntity
import com.cornflower.kotlin.utils.CustomException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 * Created by xiejingbao on 2019/5/28.
 */
class ErrorResumeFunction<T>:Function<Throwable,ObservableSource<CommonResEntity<T>>> {
    override fun apply(t: Throwable): ObservableSource<CommonResEntity<T>> {
       return Observable.error<CommonResEntity<T>>(CustomException.handleException(t))
    }
}