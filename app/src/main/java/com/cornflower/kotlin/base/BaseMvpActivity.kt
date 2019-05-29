package com.cornflower.kotlin.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.cornflower.kotlin.utils.ToastU

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



    /**
     * 显示Toast
     * @param resId 文本资源Id
     */
    fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    /**
     * 显示Toast
     * @param msg 文本
     */
    fun showToast(msg: String) {
        ToastU.showMsg(msg)
    }

    /**
     * 跳转
     * @param cls 类
     */
    fun forward(cls: Class<*>) {
        closeKeyBoard()
        forward(Intent(this, cls))
    }

    /**
     * 跳转
     * @param intent
     */
    fun forward(intent: Intent) {
        closeKeyBoard()
        startActivity(intent)
    }

    /**
     * 跳转并关闭当前页面
     * @param intent
     */
    fun forwardAndFinish(cls: Class<*>) {
        forward(cls)
        finish()
    }

    /**
     * 跳转并关闭当前页面
     */
    fun forwardAndFinish(intent: Intent) {
        forward(intent)
        finish()
    }

    /**
     * 回退
     */
    fun backward() {
        closeKeyBoard()
        finish()
    }

    /**
     * 打卡软键盘
     */
    fun openKeyBoard(mEditText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBoard(mEditText: EditText) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBoard() {
        val view: View? = window.peekDecorView()
        view?.apply {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}