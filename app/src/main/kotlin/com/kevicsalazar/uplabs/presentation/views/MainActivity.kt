package com.kevicsalazar.uplabs.presentation.views

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BaseActivity
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.ActivityComponent
import com.kevicsalazar.uplabs.utils.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        showMaterialUpFragment()
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_materialup -> consume { showMaterialUpFragment() }
                R.id.action_iosup      -> consume { showIOSUPFragment() }
                R.id.action_siteup     -> consume { showSiteUpFragment() }
                else                   -> false
            }
        }
    }

    override val layout: Int get() = R.layout.activity_main

    override val presenter: BasePresenter<*>? get() = null

    override fun setupComponent(component: ActivityComponent) = component.inject(this)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_browser -> consume { browse("https://www.uplabs.com/") }
        else                -> super.onOptionsItemSelected(item)
    }

    fun showMaterialUpFragment() {
        setupView(R.drawable.bg_material_tab, R.color.material)
        replaceContentFragment(R.id.layoutContent, PageFragment().withArguments("type" to "material"))
    }

    fun showIOSUPFragment() {
        setupView(R.drawable.bg_ios_tab, R.color.ios)
        replaceContentFragment(R.id.layoutContent, PageFragment().withArguments("type" to "ios"))
    }

    fun showSiteUpFragment() {
        setupView(R.drawable.bg_site_tab, R.color.site)
        replaceContentFragment(R.id.layoutContent, PageFragment().withArguments("type" to "site"))
    }

    fun setupView(drawableResId: Int, colorResId: Int) {

        bottomNavigation.itemIconTintList = colorStateListRes(drawableResId)
        bottomNavigation.itemTextColor = colorStateListRes(drawableResId)

        val currentColor = (toolbar.background as ColorDrawable).color
        val anim = ValueAnimator.ofObject(ArgbEvaluator(), currentColor, colorRes(colorResId))
        anim.duration = 250
        anim.addUpdateListener {
            val color = it.animatedValue as Int
            setStatusBarColor(GetMaterialColors(color).C700)
            toolbar.setBackgroundColor(color)
        }
        anim.start()
    }

}
