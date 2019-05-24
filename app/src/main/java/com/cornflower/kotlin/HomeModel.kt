package com.cornflower.kotlin

import com.cornflower.kotlin.base.CommonResEntity
import com.cornflower.kotlin.net.RetrofitManager
import io.reactivex.Observable

/**
 * Created by xiejingbao on 2019/5/22.
 */
class HomeModel{
   fun HomeModelData():Observable<CommonResEntity<Unit>> = RetrofitManager.service.homeData()
}