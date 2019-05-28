package com.cornflower.kotlin.rxScheduler

import com.cornflower.kotlin.base.CommonResEntity
import io.reactivex.ObservableTransformer

/**
 * Created by xiejingbao on 2019/5/22.
 */
object RxTransformer {
    fun <T> handleResult(): ObservableTransformer<CommonResEntity<T>, T> {
         return ObservableTransformer{
             it.onErrorResumeNext(ErrorResumeFunction()).flatMap(ResponseFunction())
         }
    }
}

