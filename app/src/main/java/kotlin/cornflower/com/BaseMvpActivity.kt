package kotlin.cornflower.com

import android.os.Bundle

abstract class BaseMvpActivity <P:Presenter<V>,V:BaseView> :BaseActivity(),BaseView{
    private var presenter: P? = null

    abstract fun initPresenter():P
    abstract fun saveInstanceState(outState: Bundle?)
    abstract fun initData(savedInstanceState: Bundle?)
    abstract fun contentView():Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        presenter = initPresenter()
        presenter?.attachView(this as?V)
        initData(savedInstanceState)
    }


    override fun onDestroy() {
        presenter?.detachView()
        presenter = null
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState)
    }
}