package com.kevicsalazar.uplabs.presentation

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    /**
     * The onCreate base will set the view specified in [layout] and will
     * inject dependencies and views.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)
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
    protected abstract val presenter: BasePresenter?

}