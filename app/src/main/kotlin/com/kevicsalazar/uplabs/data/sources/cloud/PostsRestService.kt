package com.kevicsalazar.uplabs.data.sources.cloud

import com.kevicsalazar.uplabs.data.model.Post
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.POST
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
                .request("https://$type.uplabs.com/posts", "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    interface Service {

        @POST("{path}")
        fun request(@Path(value = "path", encoded = true) path: String, @Query("page") page: String): Observable<List<Post>>

    }

}