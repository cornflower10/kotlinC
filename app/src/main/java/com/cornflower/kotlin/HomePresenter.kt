package com.cornflower.kotlin

import com.cornflower.kotlin.base.BaseMvpPresenter
import com.cornflower.kotlin.rxScheduler.SchedulerUtils

/**
 * Created by xiejingbao on 2019/5/20.
 */
class HomePresenter: BaseMvpPresenter<HomeModel,HomeView>(){

    fun homeData(){

        getModel()?.
                HomeModelData()?.
                compose(SchedulerUtils.ioToMain())?.subscribe({data->
            getView()?.let{
                it.dataSuccess(data)
            }
        }, { t->getView()?.let {
           it.onError(t.message)
        }})?.let { addSubscription(it) }
    }

}