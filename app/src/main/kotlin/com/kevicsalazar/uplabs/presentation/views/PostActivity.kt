package com.kevicsalazar.uplabs.presentation.views

import android.os.Bundle
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BaseActivity
import com.kevicsalazar.uplabs.utils.extensions.consume
import com.kevicsalazar.uplabs.utils.extensions.showHomeButton
import kotlinx.android.synthetic.main.activity_post.*

/**
 * Created by Kevin.
 */
class PostActivity : BaseActivity<PostViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        showHomeButton()
    }

    override fun getLayout() = R.layout.activity_post

    override fun getViewModelClass() = PostViewModel::class

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else              -> super.onOptionsItemSelected(item)
    }

}