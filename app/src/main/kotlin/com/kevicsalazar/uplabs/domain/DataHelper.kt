package com.kevicsalazar.uplabs.domain

import android.content.SharedPreferences
import com.google.gson.JsonArray
import com.kevicsalazar.uplabs.PerApp
import com.kevicsalazar.uplabs.domain.model.Post
import com.kevicsalazar.uplabs.utils.extensions.any
import com.kevicsalazar.uplabs.utils.extensions.array
import com.kevicsalazar.uplabs.utils.extensions.json
import com.kevicsalazar.uplabs.utils.extensions.put
import javax.inject.Inject

/**
 * Created by Kevin.
 */

@PerApp
class DataHelper @Inject constructor(val pref: SharedPreferences) {

    fun setPosts(type: String, posts: JsonArray) {
        pref.put(type, posts)
    }

    fun getPosts(type: String): List<Post>? {
        return pref.any<List<Post>>(type)
    }

    fun getPost(type: String, id: String): Post? {
        return getPosts(type)?.find { it.id == id }
    }

}