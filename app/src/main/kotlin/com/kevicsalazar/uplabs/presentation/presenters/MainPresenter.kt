package com.kevicsalazar.uplabs.presentation.presenters


import android.support.v4.app.Fragment
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.views.PageFragment
import com.kevicsalazar.uplabs.utils.extensions.withArguments
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class MainPresenter @Inject constructor(val view: View) : BasePresenter {

    var idSelected = -1

    fun prepareToShow(itemId: Int): Boolean {
        if (idSelected != itemId) {
            when (itemId) {
                R.id.action_materialup -> {
                    view.showFragmentView(PageFragment().withArguments("type" to "material"))
                    view.setupColor(R.drawable.bg_material_tab)
                    view.startColorTransition(R.color.material)
                }
                R.id.action_iosup      -> {
                    view.showFragmentView(PageFragment().withArguments("type" to "ios"))
                    view.setupColor(R.drawable.bg_ios_tab)
                    view.startColorTransition(R.color.ios)
                }
                R.id.action_siteup     -> {
                    view.showFragmentView(PageFragment().withArguments("type" to "web"))
                    view.setupColor(R.drawable.bg_site_tab)
                    view.startColorTransition(R.color.site)
                }
            }
            idSelected = itemId
        }
        return true
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }

    interface View {

        fun showFragmentView(fragment: Fragment)

        fun setupColor(drawableResId: Int)

        fun startColorTransition(colorResId: Int)

    }

}