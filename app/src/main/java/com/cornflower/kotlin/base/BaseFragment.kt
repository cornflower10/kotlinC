package com.cornflower.kotlin.base

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.cornflower.kotlin.utils.ToastU

/**
 * Created by xiejingbao on 2019/5/31.
 */
open class BaseFragment:Fragment() {
     var mContext: Context? = null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }


    /**
     * 显示Toast
     * @param resId 文本资源Id
     */
    fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    /**
     * 显示Toast
     * @param msg 文本
     */
    fun showToast(msg: String) {
        ToastU.showMsg(msg)
    }

    /**
     * 跳转
     * @param cls 类
     */
    fun forward(cls: Class<*>) {
        forward(Intent(this.activity, cls))
    }

    /**
     * 跳转
     * @param intent
     */
    fun forward(intent: Intent) {
        startActivity(intent)
    }
}