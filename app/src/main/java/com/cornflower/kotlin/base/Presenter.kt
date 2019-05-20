package com.cornflower.kotlin.base

interface Presenter<V: BaseView>{
    /**
     * presenter和对应的view绑定
     * @param mvpView  目标view
     */
     fun attachView(mvpView: V?)

    /**
     * presenter与view解绑
     */
     fun detachView()
}