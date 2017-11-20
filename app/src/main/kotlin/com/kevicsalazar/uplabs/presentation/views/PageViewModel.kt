package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.PostsRepository
import com.kevicsalazar.uplabs.presentation.PerActivity
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@PerActivity
class PageViewModel @Inject constructor(val rep: PostsRepository) : ViewModel() {

    private var livePostData: MutableLiveData<List<Post>>? = null

    fun loadPostList(): LiveData<List<Post>>? {
        if (livePostData == null) {
            livePostData = MutableLiveData()
        }
        return livePostData
    }

    fun getPosts(type: String, force: Boolean = false) {
        rep.getPostsList(type, force)
                .subscribe({
                    livePostData?.value = it
                }, {
                    it.printStackTrace()
                })
    }

}