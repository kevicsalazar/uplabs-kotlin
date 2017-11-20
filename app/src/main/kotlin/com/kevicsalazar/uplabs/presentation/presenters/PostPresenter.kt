package com.kevicsalazar.uplabs.presentation.presenters


import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.PostsDataRepository
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.BaseView
import javax.inject.Inject

/**
 * Created by Kevin.
 */
class PostPresenter @Inject constructor(val view: View, val useCase: PostsDataRepository) : BasePresenter {

    fun getPost(type: String, id: String) {
        useCase.getPostDetail(type, id)
                .subscribe({
                    view.setupPostImage(it)
                    view.showPostInfo(it)
                    view.setupButtons(it)
                }, {
                    view.showMessage("Error", it.message())
                    view.hideProgress()
                })
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }

    interface View : BaseView {

        fun setupPostImage(post: Post)

        fun showPostInfo(post: Post)

        fun setupButtons(post: Post)

    }

}