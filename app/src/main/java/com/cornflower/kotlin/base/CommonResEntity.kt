package com.cornflower.kotlin.base

/**
 * Created by xiejingbao on 2019/5/23.
 */
data  class CommonResEntity<T>(var success:Boolean, var code:String, var message :String, var extra:Unit, var data:T)