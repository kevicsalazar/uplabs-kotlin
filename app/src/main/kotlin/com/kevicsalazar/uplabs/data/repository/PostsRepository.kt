package com.kevicsalazar.uplabs.data.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.remote.PostsService
import com.kevicsalazar.uplabs.data.repository.room.PostDao
import com.kevicsalazar.uplabs.utils.extensions.TimeInterval
import com.kevicsalazar.uplabs.utils.extensions.long
import com.kevicsalazar.uplabs.utils.extensions.now
import com.kevicsalazar.uplabs.utils.extensions.put
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@Singleton
class PostsRepository @Inject constructor(val rs: PostsService, val dao: PostDao, val pref: SharedPreferences) {

    fun getPost(id: String): LiveData<Post> {
        val mutableLiveData = MutableLiveData<Post>()
        dao.getPost(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        return mutableLiveData
    }

    fun getPosts(platform: String): LiveData<List<Post>> {
        val mutableLiveData = MutableLiveData<List<Post>>()
        dao.getPosts(platform)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mutableLiveData.value = it
                }, {
                    it.printStackTrace()
                })
        refreshPosts(platform)
        return mutableLiveData
    }

    fun refreshPosts(platform: String, forced: Boolean = false) {
        if (shouldFetch(platform) || forced) {
            rs.getPosts(platform)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        dao.savePosts(it)
                        pref.put(platform, now())
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    private fun shouldFetch(platform: String) =
            now().time - pref.long(platform) > TimeInterval.MINUTE.millis.times(30)

}