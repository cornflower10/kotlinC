package com.cornflower.kotlin

import com.cornflower.kotlin.base.BaseView
import com.cornflower.kotlin.base.CommonResEntity

/**
 * Created by xiejingbao on 2019/5/20.
 */
interface HomeView: BaseView {
  fun dataSuccess(data:CommonResEntity<Unit>)
}