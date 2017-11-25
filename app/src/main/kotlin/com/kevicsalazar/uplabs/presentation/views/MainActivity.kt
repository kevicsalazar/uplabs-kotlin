package com.kevicsalazar.uplabs.presentation.views

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BaseActivity
import com.kevicsalazar.uplabs.utils.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse

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
            R.id.action_android -> setupPageFragment("android", R.drawable.bg_android_tab, R.color.android)
            R.id.action_ios     -> setupPageFragment("ios", R.drawable.bg_ios_tab, R.color.ios)
            R.id.action_web     -> setupPageFragment("web", R.drawable.bg_web_tab, R.color.web)
        }
        return true
    }

    private fun setupPageFragment(platform: String, drawableResId: Int, colorResId: Int) {

        replaceContentFragment(R.id.layoutContent, PageFragment().withArguments("platform" to platform))

        bottomNavigation.itemIconTintList = colorStateListRes(drawableResId)
        bottomNavigation.itemTextColor = colorStateListRes(drawableResId)

        val currentColor = (toolbar.background as ColorDrawable).color
        ValueAnimator.ofObject(ArgbEvaluator(), currentColor, colorRes(colorResId)).apply {
            duration = 250
            addUpdateListener {
                val color = it.animatedValue as MaterialColor
                setStatusBarColor(color.palette().C700)
                toolbar.setBackgroundColor(color)
            }
        }.start()

    }

}
