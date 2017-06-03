package com.kevicsalazar.uplabs.presentation

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.kevicsalazar.uplabs.utils.extensions.string
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

/**
 * Created by Kevin Salazar
 */
interface BasePresenter {

    /**
     * This method will be executed on
     * [AppCompatActivity.onResume] in case presenter is attached to activity
     * [Fragment.onResume]  in case presenter is attached to fragment
     */
    fun onResume()

    /**
     * This method will be executed on
     * [AppCompatActivity.onPause] in case presenter is attached to activity
     * [Fragment.onPause]  in case presenter is attached to fragment
     */
    fun onPause()

    /**
     * This method will be executed on
     * [AppCompatActivity.onDestroy] in case presenter is detached to activity
     * [Fragment.onDestroy] in case presenter is detached to fragment
     */
    fun onDestroy()

    /**
     * Custom Message Handler
     */
    fun Throwable.message(): String {
        printStackTrace()
        when (this) {
            is IOException     -> return "No se ha podido conectar con el servidor. Comprueba tu conexión a Internet y vuelve a intentarlo."
            is HttpException   -> return try { Gson().fromJson(response().errorBody()?.string(), JsonObject::class.java)["message"].string } catch (e: Exception){ "Ha ocurrido un error" }
            //is CustomException -> return message ?: "Ha ocurrido un error"
            else               -> return "Ha ocurrido un error"
        }
    }

}
