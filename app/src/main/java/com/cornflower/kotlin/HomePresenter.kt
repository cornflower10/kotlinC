package com.cornflower.kotlin

import com.cornflower.kotlin.base.BaseMvpPresenter
import com.cornflower.kotlin.base.HomeModel
import com.cornflower.kotlin.rxScheduler.SchedulerUtils
import io.reactivex.functions.Consumer

/**
 * Created by xiejingbao on 2019/5/20.
 */
class HomePresenter: BaseMvpPresenter<HomeModel,HomeView>(){

    fun homeData(){
        getModel()?.
                HomeModelData()?.
                compose(SchedulerUtils.ioToMain())?.subscribe(Consumer {user->
            getView()?.let{
                getView()?.dataSuccess(user)
            }
        }, Consumer { t->getView()?.let {
            getView()?.onError(t.message)
        }})
    }
}