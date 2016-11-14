package com.kevicsalazar.uplabs.api.ws

import com.kevicsalazar.uplabs.base.scopes.PerApp
import com.kevicsalazar.uplabs.ui.mvp.model.Post
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@PerApp
class WebServiceMaterialPosts @Inject constructor(val service: Service) {

    fun getPosts(): Observable<List<Post>> {
        return service.getPosts("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    interface Service {

        @GET("posts")
        fun getPosts(@Query("page") page: String): Observable<List<Post>>

    }

}