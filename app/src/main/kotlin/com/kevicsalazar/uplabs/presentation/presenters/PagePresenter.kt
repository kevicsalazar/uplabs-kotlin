package com.kevicsalazar.uplabs.presentation.presenters


import com.kevicsalazar.uplabs.domain.DataHelper
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.PerActivity
import com.kevicsalazar.uplabs.domain.model.Post
import com.kevicsalazar.uplabs.repository.ws.WebServicePosts
import rx.lang.kotlin.plusAssign
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by Kevin.
 */
@PerActivity
class PagePresenter @Inject constructor(val ws1: WebServicePosts, val dh: DataHelper) : BasePresenter<PagePresenter.View>() {

    val cs = CompositeSubscription()

    fun getPosts(type: String) {
        view?.showProgress()
        getPostsFromLocal(type)
        cs += ws1.getPosts(type)
                .doOnNext {
                    dh.setPosts(type, it)
                    view?.clearAdapter()
                }
                .subscribe(
                        { getPostsFromLocal(type) },
                        { onError(it) },
                        { view?.hideProgress() }
                )
    }

    fun getPostsFromLocal(type: String) {
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
        cs.clear()
    }

    interface View : BaseView {

        fun clearAdapter()

        fun addPostToAdapter(post: Post)

    }

}