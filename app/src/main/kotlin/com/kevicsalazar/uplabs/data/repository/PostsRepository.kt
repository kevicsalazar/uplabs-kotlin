package com.kevicsalazar.uplabs.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.remote.RemotePostsDataSource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Singleton
class PostsRepository @Inject constructor(val rs: RemotePostsDataSource) {

    fun getPostsList(type: String): LiveData<List<Post>> {
        val data = MutableLiveData<List<Post>>()
        rs.getPosts(type)
                .subscribe({
                    data.value = it
                }, {
                    it.printStackTrace()
                })
        return data
    }

}