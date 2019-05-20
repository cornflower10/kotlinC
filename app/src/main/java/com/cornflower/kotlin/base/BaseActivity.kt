package com.cornflower.kotlin.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

open class BaseActivity :AppCompatActivity(){
    private var context: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

}