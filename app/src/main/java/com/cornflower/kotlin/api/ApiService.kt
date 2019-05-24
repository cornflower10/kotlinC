package com.cornflower.kotlin.api

import com.cornflower.kotlin.base.CommonResEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by xiejingbao on 2019/5/22.
 */
interface ApiService {
    @GET("marquee/riskTip")
    fun homeData():Observable<CommonResEntity<Unit>>
}