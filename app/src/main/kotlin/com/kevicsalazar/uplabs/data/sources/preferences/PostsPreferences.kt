package com.kevicsalazar.uplabs.data.sources.preferences

import android.content.SharedPreferences
import com.kevicsalazar.uplabs.data.model.Post
import com.kevicsalazar.uplabs.utils.extensions.any
import com.kevicsalazar.uplabs.utils.extensions.put
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsPreferences @Inject constructor(val pref: SharedPreferences) {

    fun setPosts(type: String, posts: List<Post>) {
        pref.put(type, posts)
    }

    fun getPosts(type: String): List<Post>? {
        return pref.any<List<Post>>(type)
    }

    fun getPost(type: String, id: String): Post? {
        return getPosts(type)?.find { it.id == id }
    }

}