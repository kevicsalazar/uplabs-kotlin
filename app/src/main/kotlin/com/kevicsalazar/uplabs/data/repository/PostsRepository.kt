package com.kevicsalazar.uplabs.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.remote.PostsService
import com.kevicsalazar.uplabs.data.repository.room.PostDao
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Singleton
class PostsRepository @Inject constructor(val rs: PostsService, val dao: PostDao) {

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