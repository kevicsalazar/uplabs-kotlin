package com.kevicsalazar.uplabs.ui.mvp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
data class Post(
        val id: String,
        val name: String,
        val points: Int,
        val submitter: Submitter,
        @SerializedName("preview_url") val previewUrl: String
)