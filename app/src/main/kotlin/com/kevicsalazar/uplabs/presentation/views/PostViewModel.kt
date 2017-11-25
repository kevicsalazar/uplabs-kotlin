package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.PostsRepository
import com.kevicsalazar.uplabs.presentation.PerActivity
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@PerActivity
class PostViewModel @Inject constructor(val rep: PostsRepository) : ViewModel() {

    private var livePostData: LiveData<Post>? = null

    fun loadPost(id: String): LiveData<Post>? {
        if (livePostData == null) {
            livePostData = rep.getPost(id)
        }
        return livePostData
    }

}