package com.kevicsalazar.uplabs.utils.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Kevin.
 */

fun <T> Call<T>.enqueue(success: (response: T) -> Unit, failure: (t: String) -> Unit) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>) {
            success(response.body())
        }
        override fun onFailure(call: Call<T>?, t: Throwable) {
            failure(t.message ?: "Unknown Error")
            t.printStackTrace()
        }
    })
}