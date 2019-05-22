package com.cornflower.kotlin.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseMvpPresenter<M,V: BaseView>: Presenter<M,V> {

    private var v: V? = null
    private var m: M? = null
    private var compositeDisposable = CompositeDisposable()

    override fun attachView(model: M?,mvpView: V?) {
        this.v = mvpView
        this.m = model
    }

    override fun detachView() {
        //保证activity结束时取消所有正在执行的订阅
        if (!compositeDisposable?.isDisposed) {
            compositeDisposable.clear()
        }
        this.m = null
        this.v = null

    }

    fun getView(): V?=this.v

    fun getModel(): M?=this.m

    fun onError(msg: String, code: String) {
        v?.onError(msg)
    }

    fun addSubscription(disposable: Disposable) {
        compositeDisposable?.add(disposable)
    }

}