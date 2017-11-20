package com.kevicsalazar.uplabs.data.repository

import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.repository.local.PostsPreferences
import com.kevicsalazar.uplabs.data.repository.remote.PostsRestService
import com.kevicsalazar.uplabs.utils.extensions.hoursBeforeNow
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsRepository @Inject constructor(val rs: PostsRestService, val pref: PostsPreferences) {

    fun getPostsList(type: String, force: Boolean): Observable<List<Post>> {
        return if (force || pref.getLastUpdated(type).hoursBeforeNow() > 6) {
            rs.getPosts(type)
                    .flatMap {
                        pref.setLastUpdated(type)
                        pref.setPosts(type, it)
                    }
        } else {
            pref.getPosts(type)
        }
    }

    fun getPostDetail(type: String, postId: String) = pref.getPost(type, postId)

}