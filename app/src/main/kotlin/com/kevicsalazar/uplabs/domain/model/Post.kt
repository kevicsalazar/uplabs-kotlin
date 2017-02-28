package com.kevicsalazar.uplabs.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
data class Post(
        val id: String,
        val name: String,
        val points: Int,
        @SerializedName("serialized_submitter") val submitter: Submitter,
        @SerializedName("preview_url") val previewUrl: String
)