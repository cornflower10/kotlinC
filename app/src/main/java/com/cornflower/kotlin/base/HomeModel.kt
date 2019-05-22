package com.cornflower.kotlin.base

import com.cornflower.kotlin.User
import com.cornflower.kotlin.net.RetrofitManager
import io.reactivex.Observable

/**
 * Created by xiejingbao on 2019/5/22.
 */
class HomeModel{
   fun HomeModelData():Observable<User> = RetrofitManager.service.homeData()
}