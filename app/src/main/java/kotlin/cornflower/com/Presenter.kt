package kotlin.cornflower.com

interface Presenter<V:BaseView>{
    /**
     * presenter和对应的view绑定
     * @param mvpView  目标view
     */
     fun attachView(mvpView: BaseView?)

    /**
     * presenter与view解绑
     */
     fun detachView()
}