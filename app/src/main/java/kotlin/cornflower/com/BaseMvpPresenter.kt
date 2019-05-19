package kotlin.cornflower.com

class BaseMvpPresenter<V:BaseView>:Presenter<V>{

    private var v: V? = null

    override fun attachView(mvpView: BaseView?) {
        this.v = mvpView
    }

    override fun detachView() {
        this.v = null
    }

    fun getView(): V? {
      return this.v
    }
    fun onError(msg: String, code: String) {
        v?.onError(msg)
    }


}