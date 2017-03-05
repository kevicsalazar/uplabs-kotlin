package com.kevicsalazar.uplabs.presentation.views

import android.os.Bundle
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.domain.model.Post
import com.kevicsalazar.uplabs.presentation.ActivityComponent
import com.kevicsalazar.uplabs.presentation.BaseActivity
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.presenters.PostPresenter
import com.kevicsalazar.uplabs.utils.extensions.*
import kotlinx.android.synthetic.main.activity_post.*
import javax.inject.Inject

/**
 * Created by Kevin.
 */
class PostActivity : BaseActivity(), PostPresenter.View {

    @Inject lateinit var mPresenter: PostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        showHomeButton()

        val type = intent.getStringExtra("type")
        val id = intent.getStringExtra("id")
        mPresenter.getPost(type, id)

        applyColor(colorRes(R.color.colorPrimary))

    }

    override val layout: Int get() = R.layout.activity_post

    override val presenter: BasePresenter<*>? get() = mPresenter.with(this)

    override fun setupComponent(component: ActivityComponent) = component.inject(this)

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else              -> super.onOptionsItemSelected(item)
    }

    override fun showPostInfo(post: Post) {
        ivPost.loadUrl(post.previewUrl)
        tvName.text = post.name
        tvDescription.text = post.description
        btnShare.setOnClickListener {
            share(getString(R.string.share_with), post.linkUrl)
        }
        btnLink.setOnClickListener {
            browse(post.linkUrl)
        }
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun showMessage(title: String, message: String) {

    }

    fun applyColor(color: Int) {
        val colorPalette = getColorPalette(color)
        collapsingToolbar.setBackgroundColor(colorPalette.first)
        collapsingToolbar.setContentScrimColor(colorPalette.first)
        collapsingToolbar.setStatusBarScrimColor(colorPalette.second)
    }
}