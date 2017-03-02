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
class PostPresenter @Inject constructor(val dh: DataHelper) : BasePresenter<PostPresenter.View>() {

    fun getPost(type: String, id: String) {
        dh.getPost(type, id)?.let {
            view?.showPostInfo(it)
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

        fun showPostInfo(post: Post)

    }

}