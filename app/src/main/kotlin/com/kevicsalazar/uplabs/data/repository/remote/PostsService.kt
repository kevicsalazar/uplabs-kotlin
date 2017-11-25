package com.kevicsalazar.uplabs.data.repository.remote

import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
interface PostsService {

    @GET("{platform}")
    fun getPosts(@Path("platform") platform: String): Observable<List<Post>>

}