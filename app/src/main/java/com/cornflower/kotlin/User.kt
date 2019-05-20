package com.cornflower.kotlin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by xiejingbao on 2019/5/20.
 *
 */
@Parcelize
data class User(var name:String):Parcelable