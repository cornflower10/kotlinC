package com.cornflower.kotlin

import com.cornflower.kotlin.base.BaseMvpPresenter
import com.cornflower.kotlin.rxScheduler.RxTransformer
import com.cornflower.kotlin.rxScheduler.SchedulerUtils
import com.cornflower.kotlin.utils.LogManager
import io.reactivex.Observable

/**
 * Created by xiejingbao on 2019/5/20.
 */
class HomePresenter: BaseMvpPresenter<HomeModel,HomeView>(){

    /**
     * 有关联性的请求用merge,第一个请求失败后面的请求就不会执行,请求异常会被捕获
     */
    fun homeData(){
        Observable.merge(getModel()?.HomeModelData(),getModel()?.bankCards("10","1"))
       .compose(RxTransformer.handleResult())?.compose(SchedulerUtils.ioToMain())?.subscribe({ data->
                    LogManager.i(data)
            getView()?.let{
                it.dataSuccess(data)
            }
        }, { t->getView()?.let {
           it.onError(t.message)
        }})?.let { addSubscription(it) }
//        getModel()?.HomeModelData()?.flatMap(Function<CommonResEntity<String>,Observable<CommonResEntity<String>>>{
//            LogManager.i("flatMap:"+it)
//           return@Function getModel()?.bankCards("10","1")
//        })
////       .compose(RxTransformer.handleResult())?.
//                ?.compose(SchedulerUtils.ioToMain())?.subscribe({ data->
//                    LogManager.i(data.data)
//                    getView()?.let{
//                        it.dataSuccess(data.data)
//                    }
//                }, { t->getView()?.let {
//                    it.onError(t.message)
//                }})?.let { addSubscription(it) }
    }

}