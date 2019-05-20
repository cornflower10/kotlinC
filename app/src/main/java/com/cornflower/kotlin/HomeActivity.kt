package com.cornflower.kotlin

import android.os.Bundle
import com.cornflower.kotlin.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.cornflower.com.R


class HomeActivity : BaseMvpActivity<HomePresenter, HomeView>(), HomeView {

    override fun initPresenter(): HomePresenter = HomePresenter()

    override fun saveInstanceState(outState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv.text = "mvp"
    }

    override fun contentView() = R.layout.activity_main


}