package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.graphics.Palette
import android.view.MenuItem
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.presentation.BaseActivity
import com.kevicsalazar.uplabs.utils.extensions.*
import kotlinx.android.synthetic.main.activity_post.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.share

/**
 * Created by Kevin.
 */
class PostActivity : BaseActivity<PostViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        showHomeButton()

        val id = intent.getStringExtra("id")
        viewModel.loadPost(id)?.observe(this, postChanges)

    }

    override fun getLayout() = R.layout.activity_post

    override fun getViewModelClass() = PostViewModel::class

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> consume { onBackPressed() }
        else              -> super.onOptionsItemSelected(item)
    }

    private val postChanges = Observer<Post> {
        it?.let {
            setupPostImage(it)
            showPostInfo(it)
            setupButtons(it)
        }
    }

    private fun setupPostImage(post: Post) {
        ivPost.load(post.previewUrl) {
            Palette.from(it).generate {
                applyColor(it.vibrantSwatch?.rgb ?: post.colorHex.asColor().palette().C700)
            }
        }
    }

    private fun showPostInfo(post: Post) {
        tvTitle.text = post.name
        tvSubtitle.text = post.submitter?.fullName ?: post.makerName
        tvDescription.text = post.description?.fromHtml()
    }

    private fun setupButtons(post: Post) {
        btnShare.setOnClickListener {
            share(post.linkUrl, post.name)
        }
        btnLink.setOnClickListener {
            browse(post.linkUrl)
        }
    }

    private fun applyColor(color: Int) {
        collapsingToolbar.setBackgroundColor(color)
        collapsingToolbar.setContentScrimColor(color)
        collapsingToolbar.setStatusBarScrimColor(color)
        toolbarWrapper.setBackgroundColor(color)
    }

}