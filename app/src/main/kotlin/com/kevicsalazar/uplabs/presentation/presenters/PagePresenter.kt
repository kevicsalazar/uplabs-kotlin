package com.kevicsalazar.uplabs.presentation.presenters


import com.kevicsalazar.uplabs.repository.ws.WebServiceIOSPosts
import com.kevicsalazar.uplabs.repository.ws.WebServiceMaterialPosts
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.PerActivity
import com.kevicsalazar.uplabs.domain.model.Post
import rx.lang.kotlin.plusAssign
import rx.lang.kotlin.toObservable
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by Kevin.
 */
@PerActivity
class PagePresenter @Inject constructor(val ws1: WebServiceMaterialPosts, val ws2: WebServiceIOSPosts) : BasePresenter<PagePresenter.View>() {

    val cs = CompositeSubscription()

    fun getPosts(type: String) {
        view?.showProgress()
        when (type) {
            "material" -> {
                cs += ws1.getPosts()
                        .doOnNext { view?.clearAdapter() }
                        .flatMap { it.toObservable() }
                        .subscribe(
                                { view?.addPostToAdapter(it) },
                                { onError(it) },
                                { view?.hideProgress() }
                        )
            }
            "ios" -> {
                cs += ws2.getPosts()
                        .doOnNext { view?.clearAdapter() }
                        .flatMap { it.toObservable() }
                        .subscribe(
                                { view?.addPostToAdapter(it) },
                                { onError(it) },
                                { view?.hideProgress() }
                        )
            }
        }

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        view == null
        cs.clear()
    }

    interface View : BaseView {

        fun clearAdapter()

        fun addPostToAdapter(post: Post)

    }

}