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
import com.kevicsalazar.uplabs.utils.extensions.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        prepareToShow(R.id.action_android)
        bottomNavigation.setOnNavigationItemSelectedListener {
            prepareToShow(it.itemId)
        }

    }

    override fun getLayout() = R.layout.activity_main

    override fun getViewModelClass() = MainViewModel::class

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_browser -> consume { browse("https://www.uplabs.com/") }
        else                -> super.onOptionsItemSelected(item)
    }

    private fun prepareToShow(itemId: Int): Boolean {
        when (itemId) {
            R.id.action_android -> {
                showFragmentView(PageFragment().withArguments("type" to "android"))
                setupColor(R.drawable.bg_android_tab)
                startColorTransition(R.color.android)
            }
            R.id.action_ios     -> {
                showFragmentView(PageFragment().withArguments("type" to "ios"))
                setupColor(R.drawable.bg_ios_tab)
                startColorTransition(R.color.ios)
            }
            R.id.action_web     -> {
                showFragmentView(PageFragment().withArguments("type" to "web"))
                setupColor(R.drawable.bg_web_tab)
                startColorTransition(R.color.web)
            }
        }
        return true
    }

    private fun showFragmentView(fragment: Fragment) {
        replaceContentFragment(R.id.layoutContent, fragment)
    }

    private fun setupColor(drawableResId: Int) {
        bottomNavigation.itemIconTintList = colorStateListRes(drawableResId)
        bottomNavigation.itemTextColor = colorStateListRes(drawableResId)
    }

    private fun startColorTransition(colorResId: Int) {
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
