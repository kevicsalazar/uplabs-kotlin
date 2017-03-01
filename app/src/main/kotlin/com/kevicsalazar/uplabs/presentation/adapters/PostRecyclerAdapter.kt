package com.kevicsalazar.uplabs.presentation.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kevicsalazar.uplabs.domain.model.Post
import android.view.LayoutInflater
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.utils.extensions.Transformation
import com.kevicsalazar.uplabs.utils.extensions.loadUrl
import kotlinx.android.synthetic.main.item_post.view.*


/**
 * Created by Kevin.
 */

class PostRecyclerAdapter : RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>() {

    private var posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val post = posts[position]
            ivPreview.loadUrl(post.previewUrl)
            ivAvatar.loadUrl(post.submitter.avatarUrl, Transformation.Circle)
            tvPostName.text = post.name
            tvSubmitterName.text = post.submitter.fullName
        }
    }

    override fun getItemCount() = posts.size

    fun add(post: Post) {
        posts.add(post)
        notifyDataSetChanged()
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
