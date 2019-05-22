package com.cornflower.kotlin.api

import com.cornflower.kotlin.User
import io.reactivex.Observable

/**
 * Created by xiejingbao on 2019/5/22.
 */
interface ApiService {
    fun homeData():Observable<User>
}