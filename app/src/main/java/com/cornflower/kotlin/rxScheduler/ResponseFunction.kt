package com.cornflower.kotlin.rxScheduler

import com.cornflower.kotlin.api.Constant
import com.cornflower.kotlin.base.CommonResEntity
import com.cornflower.kotlin.utils.ApiException
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 * Created by xiejingbao on 2019/5/28.
 */
class ResponseFunction<T> :Function<CommonResEntity<T>,ObservableSource<T>>{
    override fun apply(t: CommonResEntity<T>): ObservableSource<T>? {
        if(t.success)
        {
            return Observable.just(t.data)
        }else{
            if (Constant.CODES.contains(t.code)){
                return Observable.error<CommonResEntity<T>>(ApiException(t.code)) as? ObservableSource<T>
            }
            return Observable.error<CommonResEntity<T>>(ApiException(t.code,t.message)) as? ObservableSource<T>
        }
    }
}