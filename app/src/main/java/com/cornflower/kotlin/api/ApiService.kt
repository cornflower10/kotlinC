package com.cornflower.kotlin.api

import com.cornflower.kotlin.base.CommonResEntity
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by xiejingbao on 2019/5/22.
 */
interface ApiService {
    @GET("marquee/riskTip")
    fun homeData():Observable<CommonResEntity<String>>
    @FormUrlEncoded
    @POST("/bankcard/replace-card-history-data")
    fun bankCards(@Field("rows") rows:String,@Field("page") page:String):Observable<CommonResEntity<String>>
}