package com.kevicsalazar.uplabs.presentation.presenters


import com.kevicsalazar.uplabs.domain.DataHelper
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.PerActivity
import com.kevicsalazar.uplabs.domain.model.Post
import com.kevicsalazar.uplabs.repository.ws.WebServicePosts
import javax.inject.Inject

/**
 * Created by Kevin.
 */
@PerActivity
class PagePresenter @Inject constructor(val ws1: WebServicePosts, val dh: DataHelper) : BasePresenter<PagePresenter.View>() {

    fun getPosts(type: String) {
        view?.showProgress()
        ws1.getPosts(type, {
            dh.setPosts(type, it)
            view?.hideProgress()
            getPostsFromLocal(type)
        }, {
            view?.hideProgress()
            view?.showMessage("Error", it)
        })
    }

    fun getPostsFromLocal(type: String) {
        view?.clearAdapter()
        dh.getPosts(type)?.forEach {
            view?.addPostToAdapter(it)
        }
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        view = null
    }

    interface View : BaseView {

        fun clearAdapter()

        fun addPostToAdapter(post: Post)

    }

}