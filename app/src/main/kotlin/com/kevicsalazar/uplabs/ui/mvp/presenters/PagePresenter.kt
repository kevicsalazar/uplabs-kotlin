package com.kevicsalazar.uplabs.ui.mvp.presenters


import com.kevicsalazar.uplabs.api.ws.WebServiceIOSPosts
import com.kevicsalazar.uplabs.api.ws.WebServiceMaterialPosts
import com.kevicsalazar.uplabs.base.BasePresenter
import com.kevicsalazar.uplabs.base.scopes.PerActivity
import com.kevicsalazar.uplabs.ui.mvp.model.Post
import rx.lang.kotlin.plusAssign
import rx.lang.kotlin.toObservable
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

/**
 * Created by Kevin.
 */
@PerActivity
class PagePresenter @Inject constructor(val ws1: WebServiceMaterialPosts, val ws2: WebServiceIOSPosts) : BasePresenter<PagePresenter.UI>() {

    val cs = CompositeSubscription()

    fun getPosts(type: String) {
        ui?.showProgress()
        when(type){
            "material" -> {
                cs += ws1.getPosts()
                        .doOnNext { ui?.clearAdapter() }
                        .flatMap { it.toObservable() }
                        .subscribe(
                                { ui?.addPostToAdapter(it) },
                                { onError(it) },
                                { ui?.hideProgress() }
                        )
            }
            "ios" -> {
                cs += ws2.getPosts()
                        .doOnNext { ui?.clearAdapter() }
                        .flatMap { it.toObservable() }
                        .subscribe(
                                { ui?.addPostToAdapter(it) },
                                { onError(it) },
                                { ui?.hideProgress() }
                        )
            }
        }

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {
        ui == null
        cs.clear()
    }

    interface UI : BaseUI {

        fun clearAdapter()

        fun addPostToAdapter(post: Post)

    }

}