package com.cornflower.kotlin

import android.os.Bundle
import com.bumptech.glide.Glide
import com.cornflower.kotlin.base.BaseMvpActivity
import com.cornflower.kotlin.utils.ToastU
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.cornflower.com.R


class HomeActivity : BaseMvpActivity<HomeModel, HomeView,HomePresenter>(), HomeView {
    override fun dataSuccess(data: String) {
//       tv.text = data
    }


    override fun initModel(): HomeModel?  = HomeModel()

    override fun initPresenter(): HomePresenter = HomePresenter()

    override fun saveInstanceState(outState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
//        tv.text = "mvp"
//        presenter?.homeData()
//        tv.setOnClickListener{ forward(MainActivity::class.java) }
        Glide.with(this)
                .load("http://172.16.123.163:7788/static/images/help_complaints.png").listener(Listener()).into(iv)
    }

    override fun contentView() = R.layout.activity_main

    override fun onError(msg: String?) {
        super.onError(msg)
        ToastU.showMsg(msg)
    }
}