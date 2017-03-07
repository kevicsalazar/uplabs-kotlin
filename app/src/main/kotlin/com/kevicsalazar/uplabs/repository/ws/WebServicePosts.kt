package com.kevicsalazar.uplabs.repository.ws

import com.google.gson.JsonArray
import com.kevicsalazar.uplabs.PerApp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
@PerApp
class WebServicePosts @Inject constructor(val service: Service) {

    fun getPosts(type: String, onSuccess: (JsonArray) -> Unit, onFailure: (String) -> Unit) {

        service.getPosts("https://$type.uplabs.com/posts", "1").enqueue(object : Callback<JsonArray> {

            override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    onFailure("Unknown Error")
                }
            }

            override fun onFailure(call: Call<JsonArray>?, t: Throwable) {
                onFailure(t.message ?: "Unknown Error")
            }
        })
    }

    interface Service {

        @GET("{path}")
        fun getPosts(@Path(value = "path", encoded = true) path: String, @Query("page") page: String): Call<JsonArray>

    }

}