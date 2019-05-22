package com.cornflower.kotlin.base

interface Presenter<M,V: BaseView>{
    /**
     * presenter和对应的view绑定
     * @param mvpView  目标view
     */
     fun attachView(model: M?,mvpView: V?)

    /**
     * presenter与view解绑
     */
     fun detachView()

}