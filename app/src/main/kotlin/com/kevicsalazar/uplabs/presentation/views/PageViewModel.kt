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
class PageViewModel @Inject constructor(val rep: PostsRepository) : ViewModel() {

    private var livePostData: LiveData<List<Post>>? = null

    fun loadPosts(platform: String): LiveData<List<Post>>? {
        Log.e(":)", "" + (livePostData != null))
        if (livePostData == null) {
            livePostData = rep.getPosts(platform)
        }
        return livePostData
    }

}