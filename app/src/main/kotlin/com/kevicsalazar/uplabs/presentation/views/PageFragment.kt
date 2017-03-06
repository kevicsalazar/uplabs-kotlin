package com.kevicsalazar.uplabs.presentation.views

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kevicsalazar.uplabs.R
import com.kevicsalazar.uplabs.presentation.BaseFragment
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.ActivityComponent
import com.kevicsalazar.uplabs.presentation.adapters.PostRecyclerAdapter
import com.kevicsalazar.uplabs.domain.model.Post
import com.kevicsalazar.uplabs.presentation.presenters.PagePresenter
import com.kevicsalazar.uplabs.utils.extensions.alert
import kotlinx.android.synthetic.main.fragment_page.*
import javax.inject.Inject

/**
 * Created by Kevin.
 */
class PageFragment : BaseFragment(), PagePresenter.View {

    @Inject lateinit var mPresenter: PagePresenter

    var postAdapter: PostRecyclerAdapter? = null

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val type = arguments.getString("type")

        postAdapter = PostRecyclerAdapter(activity, type)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = postAdapter

        mPresenter.getPosts(type)

    }

    override val layout: Int get() = R.layout.fragment_page

    override val presenter: BasePresenter<*>? get() = mPresenter.with(this)

    override fun setupComponent(component: ActivityComponent) = component.inject(this)

    override fun clearAdapter() {
        postAdapter?.clear()
    }

    override fun addPostToAdapter(post: Post) {
        postAdapter?.add(post)
    }

    override fun showProgress() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        swipeRefresh.isRefreshing = false
    }

    override fun showMessage(title: String, message: String) {
        alert(title, message).show()
    }

}