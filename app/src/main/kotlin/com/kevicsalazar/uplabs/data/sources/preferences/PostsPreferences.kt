package com.kevicsalazar.uplabs.data.sources.preferences

import android.content.SharedPreferences
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.utils.extensions.any
import com.kevicsalazar.uplabs.utils.extensions.long
import com.kevicsalazar.uplabs.utils.extensions.put
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsPreferences @Inject constructor(val pref: SharedPreferences) {

    var lastUpdated: Date
        get() = Date(pref.long("lastUpdated"))
        set(value) {
            pref.put("lastUpdated", value)
        }

    fun setPosts(type: String, posts: List<Post>): Observable<List<Post>> {
        return Observable.create {
            try {
                pref.put(type, posts)
                it.onNext(posts)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun getPosts(type: String): Observable<List<Post>> {
        return Observable.create {
            it.onNext(pref.any<List<Post>>(type))
        }
    }

    fun getPost(type: String, postId: String): Observable<Post> {
        return getPosts(type).map { it.find { it.id == postId } }
    }

}