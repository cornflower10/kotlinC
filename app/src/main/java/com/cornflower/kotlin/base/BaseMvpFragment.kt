package com.cornflower.kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseMvpFragment <M,V: BaseView,P: Presenter<M,V>> : BaseFragment(), BaseView{
    var presenter: P? = null
    private var model: M? = null
    abstract fun contentView():Int
    //初始化view
    abstract fun init()
    abstract fun initModel(): M?
    abstract fun initPresenter():P
    abstract fun initData(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var  view:View =   inflater.inflate(contentView(), container, false)
        init()
        model = initModel()
        presenter = initPresenter()
        presenter?.attachView(model,this as?V)
        initData(savedInstanceState)
        return view
    }

    override fun onDestroyView() {
        presenter?.detachView()
        presenter = null
        super.onDestroyView()
    }

    override fun onError(msg: String?) {
    }


}