package com.kevicsalazar.uplabs.presentation.presenters


import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.PostsDataRepository
import com.kevicsalazar.uplabs.presentation.BasePresenter
import com.kevicsalazar.uplabs.presentation.BaseView
import javax.inject.Inject

/**
 * Created by Kevin.
 */
class PagePresenter @Inject constructor(val view: View, val useCase: PostsDataRepository) : BasePresenter {

    fun getPosts(type: String, force: Boolean = false) {
        view.showProgress()
        useCase.getPostsList(type, force)
                .subscribe({
                    view.clearAdapter()
                    it.forEach { view.addPostToAdapter(it) }
                    view.hideProgress()
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

        fun clearAdapter()

        fun addPostToAdapter(post: Post)

    }

}