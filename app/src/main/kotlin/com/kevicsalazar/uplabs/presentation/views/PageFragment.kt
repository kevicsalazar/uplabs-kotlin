package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BaseFragment
import com.kevicsalazar.uplabs.presentation.views.adapters.PostRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_page.*

/**
 * Created by Kevin.
 */
class PageFragment : BaseFragment<PageViewModel>() {

    var postAdapter: PostRecyclerAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments.getString("type")

        postAdapter = PostRecyclerAdapter(activity, type)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = postAdapter

        viewModel.loadPostList(type)?.observe(this, Observer {
            Log.e("New", "New")
            postAdapter?.clear()
            it?.forEach { postAdapter?.add(it) }
        })

        swipeRefresh.setOnRefreshListener {

        }

    }

    override fun getLayout() = R.layout.fragment_page

    override fun getViewModelClass() = PageViewModel::class

}