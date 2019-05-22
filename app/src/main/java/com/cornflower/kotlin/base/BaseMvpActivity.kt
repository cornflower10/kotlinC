package com.cornflower.kotlin.base

import android.os.Bundle

abstract class BaseMvpActivity <M,V: BaseView,P: Presenter<M,V>> : BaseActivity(), BaseView{
    private var presenter: P? = null
    private var model: M? = null
    abstract fun initModel(): M?
    abstract fun initPresenter():P
    abstract fun saveInstanceState(outState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)
    abstract fun contentView():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        model = initModel()
        presenter = initPresenter()
        presenter?.attachView(model,this as?V)
        initData(savedInstanceState)
    }




    override fun onDestroy() {
        presenter?.detachView()
        presenter = null
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        saveInstanceState(outState)
        super.onSaveInstanceState(outState)

    }

    override fun onError(msg: String) {
    }
}