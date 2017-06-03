package com.kevicsalazar.uplabs.presentation.views

import android.os.Bundle
import android.support.v7.graphics.Palette
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.data.model.Post
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

    }

    override val layout: Int get() = R.layout.activity_post

    override val presenter: BasePresenter? get() = mPresenter

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else              -> super.onOptionsItemSelected(item)
    }

    override fun setupPostImage(post: Post) {
        ivPost.loadUrl(post.previewUrl) {
            Palette.from(it).generate {
                applyColor(it.vibrantSwatch?.rgb ?: post.colorHex.toColor().palette().C700)
            }
        }
    }

    override fun showPostInfo(post: Post) {
        tvTitle.text = post.name
        tvSubtitle.text = post.maker?.fullName ?: post.makerName
        tvDescription.text = post.description?.fromHtml()
    }

    override fun setupButtons(post: Post) {
        btnShare.setOnClickListener {
            share(post.linkUrl, post.name)
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
        collapsingToolbar.setBackgroundColor(color)
        collapsingToolbar.setContentScrimColor(color)
        collapsingToolbar.setStatusBarScrimColor(color)
        toolbarWrapper.setBackgroundColor(color)
    }

}