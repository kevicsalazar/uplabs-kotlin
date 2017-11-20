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

        val type = intent.getStringExtra("type")
        val id = intent.getStringExtra("id")
        //mPresenter.getPost(type, id)

    }

    override fun getLayout() = R.layout.activity_post

    override fun getViewModelClass() = PostViewModel::class

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else              -> super.onOptionsItemSelected(item)
    }

    /*override fun setupPostImage(post: Post) {
        ivPost.load(post.previewUrl) {
            Palette.from(it).generate {
                applyColor(it.vibrantSwatch?.rgb ?: post.colorHex.toColor().palette().C700)
            }
        }
    }

    override fun showPostInfo(post: Post) {
        tvTitle.text = post.name
        tvSubtitle.text = post.submitter?.fullName ?: post.makerName
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
    }*/

}