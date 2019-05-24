package com.cornflower.kotlin

import android.util.Log
import com.cornflower.kotlin.base.BaseMvpPresenter
import com.cornflower.kotlin.rxScheduler.SchedulerUtils

/**
 * Created by xiejingbao on 2019/5/20.
 */
class HomePresenter: BaseMvpPresenter<HomeModel,HomeView>(){

    fun homeData(){
        try {


        getModel()?.
                HomeModelData()?.
                compose(SchedulerUtils.ioToMain())?.subscribe({data->
            getView()?.let{
                getView()?.dataSuccess(data)
            }
        }, { t->getView()?.let {
            getView()?.onError(t.message)
        }})
        } catch (e:Exception){
            Log.e("e",e.message)
        }
    }
}