package com.kevicsalazar.uplabs.presentation.presenters

import com.kevicsalazar.uplabs.presentation.BasePresenter
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class MainPresenter @Inject constructor(val view: View) : BasePresenter {

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }

    interface View {

        fun showMaterialUpFragment()

        fun showIOSUPFragment()

        fun showSiteUpFragment()

    }

}