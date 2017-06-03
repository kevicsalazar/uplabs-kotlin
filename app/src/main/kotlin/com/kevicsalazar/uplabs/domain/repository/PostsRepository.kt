package com.kevicsalazar.uplabs.domain.repository

import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Observable

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
interface PostsRepository {

    fun getPostsList(type: String, force: Boolean = false): Observable<List<Post>>

    fun getPostDetail(type: String, postId: String): Observable<Post>

}