package com.cornflower.kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseMvpLazyFragment <M,V: BaseView,P: Presenter<M,V>> : BaseFragment(), BaseView{
    var presenter: P? = null
    private var model: M? = null
    abstract fun contentView():Int
    //初始化view
    abstract fun init()
    abstract fun initModel(): M?
    abstract fun initPresenter():P
//    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun onFragmentVisibleChange(isVisible: Boolean)

    private var isFragmentVisible: Boolean = false

    private var isViewCreated: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var  view:View =   inflater.inflate(contentView(), container, false)
        init()
        isViewCreated = true
        model = initModel()
        presenter = initPresenter()
        presenter?.attachView(model,this as?V)
        lazyLoad()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            isFragmentVisible = true
            lazyLoad()
        } else {
            isFragmentVisible = false
        }
    }

    private fun lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isFragmentVisible) {

            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false
            isFragmentVisible = false
            onFragmentVisibleChange(true)

        }
    }

    override fun onDestroyView() {
        presenter?.detachView()
        presenter = null
        super.onDestroyView()
    }

    override fun onError(msg: String?) {
    }


}