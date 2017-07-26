package com.kevicsalazar.uplabs.data.sources.cloud

import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class PostsRestService @Inject constructor(val service: Service) {

    fun getPosts(type: String): Observable<List<Post>> {
        return service
                .request(type, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    interface Service {

        @GET("{type}")
        fun request(@Path("type") type: String, @Query("page") page: String): Observable<List<Post>>

    }

}