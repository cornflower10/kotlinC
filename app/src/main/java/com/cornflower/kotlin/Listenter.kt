package com.cornflower.kotlin

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.cornflower.kotlin.utils.LogManager

/**
 * Created by xiejingbao on 2019/6/25.
 */
class Listener :RequestListener<Drawable>{
    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
       LogManager.e("e",e)

        return  false

    }

    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
         LogManager.i("resourceintrinsicHeight"+resource?.intrinsicHeight)
        LogManager.i("resource"+resource?.intrinsicWidth)
          return false


    }

}