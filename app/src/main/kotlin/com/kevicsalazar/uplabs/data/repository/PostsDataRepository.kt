package com.kevicsalazar.uplabs.data.repository

import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.data.sources.cloud.PostsRestService
import com.kevicsalazar.uplabs.data.sources.preferences.PostsPreferences
import com.kevicsalazar.uplabs.domain.repository.PostsRepository
import com.kevicsalazar.uplabs.utils.extensions.hoursBeforeNow
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsDataRepository @Inject constructor(val rs: PostsRestService, val pref: PostsPreferences) : PostsRepository {

    override fun getPostsList(type: String, force: Boolean): Observable<List<Post>> {

        if (force || pref.lastUpdated.hoursBeforeNow() > 6) {
            return rs.getPosts(type)
                    .flatMap {
                        pref.lastUpdated = Date()
                        pref.setPosts(type, it)
                    }
        } else {
            return pref.getPosts(type)
        }

    }

    override fun getPostDetail(type: String, postId: String): Observable<Post> {
        return pref.getPost(type, postId)

    }

}