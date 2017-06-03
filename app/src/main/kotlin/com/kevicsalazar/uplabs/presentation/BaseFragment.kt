package com.kevicsalazar.uplabs.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by Kevin Salazar
 */
abstract class BaseFragment : DaggerFragment() {

    /**
     * The onCreateView base will set the view specified in [layout].
     */
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater!!.inflate(layout, container, false)
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
     * Specify the layout of the fragment to be inflated in the [BaseFragment.onCreateView]
     */
    protected abstract val layout: Int

    /**
     * @return The presenter attached to the fragment. This must extends from [BasePresenter]
     */
    protected abstract val presenter: BasePresenter?

}
