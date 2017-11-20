package com.kevicsalazar.uplabs.presentation.views.adapters

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.presentation.views.PostActivity
import com.kevicsalazar.uplabs.utils.extensions.load
import com.kevicsalazar.uplabs.utils.extensions.loadCircle
import com.kevicsalazar.uplabs.utils.extensions.startActivity
import kotlinx.android.synthetic.main.item_post.view.*


/**
 * Created by Kevin.
 */

class PostRecyclerAdapter(val act: Activity, val type: String) : RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder>() {

    private var posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val post = posts[position]
            tvPostName.text = post.name
            ivPreview.load(post.previewUrl)
            tvMakerName.text = String.format("by %s", post.submitter?.fullName ?: post.makerName ?: "")
            ivAvatar.loadCircle(post.submitter?.avatarUrl)
            tvPoints.text = post.points.toString()
            setOnClickListener {
                act as AppCompatActivity
                act.startActivity<PostActivity>(
                        listOf("type" to type, "id" to post.id),
                        listOf(ivPreview to "preview")
                )
            }
        }
    }

    override fun getItemCount() = posts.size

    fun add(post: Post) {
        this.posts.add(post)
        notifyItemInserted(posts.size)
    }

    fun clear() {
        this.posts.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
