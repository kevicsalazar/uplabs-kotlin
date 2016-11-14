package com.kevicsalazar.uplabs.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.kevicsalazar.uplabs.ui.ActivityComponent

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */

abstract class BaseActivity : AppCompatActivity() {

    /**
     * The onCreate base will set the view specified in [layout] and will
     * inject dependencies and views.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponent(ActivityComponent.Initializer.init(this))
        setContentView(layout)
        presenter
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    /**
     * @return The layout that's gonna be the activity view.
     */
    protected abstract val layout: Int

    /**
     * @return The presenter attached to the activity. This must extends from [BasePresenter]
     */
    protected abstract val presenter: BasePresenter<*>?

    /**
     * This method will setup the injector and will commit the dependencies injections.
     */
    protected abstract fun setupComponent(component: ActivityComponent)


    protected fun setTranslucentStatusBar() {
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    protected fun setBackgroundColor(resIdColor: Int) {
        window.setBackgroundDrawableResource(resIdColor)
    }

}