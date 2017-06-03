package com.kevicsalazar.uplabs.domain.repository

import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Observable

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
interface PostsRepository {

    fun getPostsList(type: String): Observable<List<Post>>

}