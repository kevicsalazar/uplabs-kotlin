package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.presentation.BaseFragment
import com.kevicsalazar.uplabs.presentation.views.adapters.PostRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_page.*

/**
 * Created by Kevin.
 */
class PageFragment : BaseFragment<PageViewModel>() {

    lateinit var postAdapter: PostRecyclerAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val platform = arguments.getString("platform")

        postAdapter = PostRecyclerAdapter(activity)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = postAdapter

        viewModel.platform = platform
        viewModel.loadPosts()?.observe(this, postsChanges)

        swipeRefresh.setOnRefreshListener {
            viewModel.refreshPost()
        }

    }

    override fun getLayout() = R.layout.fragment_page

    override fun getViewModelClass() = PageViewModel::class

    private val postsChanges = Observer<List<Post>> {
        postAdapter.updateList(it ?: listOf())
        swipeRefresh.isRefreshing = false
    }

}