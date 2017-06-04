package com.kevicsalazar.uplabs.presentation.views

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BaseActivity
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.presenters.MainPresenter
import com.kevicsalazar.uplabs.utils.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.View {

    @Inject lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        mPresenter.prepareToShow(R.id.action_materialup)
        bottomNavigation.setOnNavigationItemSelectedListener {
            mPresenter.prepareToShow(it.itemId)
        }

    }

    override val layout: Int get() = R.layout.activity_main

    override val presenter: BasePresenter? get() = mPresenter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_browser -> consume { browse("https://www.uplabs.com/") }
        else                -> super.onOptionsItemSelected(item)
    }

    override fun showFragmentView(fragment: Fragment) {
        replaceContentFragment(R.id.layoutContent, fragment)
    }

    override fun setupColor(drawableResId: Int) {
        bottomNavigation.itemIconTintList = colorStateListRes(drawableResId)
        bottomNavigation.itemTextColor = colorStateListRes(drawableResId)
    }

    override fun startColorTransition(colorResId: Int) {
        val currentColor = (toolbar.background as ColorDrawable).color
        val anim = ValueAnimator.ofObject(ArgbEvaluator(), currentColor, colorRes(colorResId))
        anim.duration = 250
        anim.addUpdateListener {
            val color = it.animatedValue as MaterialColor
            setStatusBarColor(color.palette().C700)
            toolbar.setBackgroundColor(color)
        }
        anim.start()
    }

}
