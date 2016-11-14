package com.kevicsalazar.uplabs.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


/**
 * @author Kevin Salazar
 * *
 * @link kevicsalazar.com
 */
class SimplePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private var fragmentList = mutableListOf<Fragment>()
    private var titleList = mutableListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }

}
