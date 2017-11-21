package com.kevicsalazar.uplabs.presentation.views

import android.arch.lifecycle.LiveData
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

    private var livePostData: LiveData<List<Post>>? = null

    fun loadPostList(type: String): LiveData<List<Post>>? {
        if (livePostData == null) {
            livePostData = rep.getPostsList(type)
        }
        return livePostData
    }

}