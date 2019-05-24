package com.cornflower.kotlin.base

import android.os.Bundle

abstract class BaseMvpActivity <M,V: BaseView,P: Presenter<M,V>> : BaseActivity(), BaseView{
    var presenter: P? = null
    private var model: M? = null
    abstract fun contentView():Int
    abstract fun initModel(): M?
    abstract fun initPresenter():P
    abstract fun saveInstanceState(outState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)
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

    override fun onError(msg: String?) {
    }
}