package com.kevicsalazar.uplabs.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
data class Post(
        val id: String,
        val name: String,
        val points: Int,
        @SerializedName("maker_name") val makerName: String,
        @SerializedName("serialized_maker") val maker: Maker?,
        @SerializedName("preview_url") val previewUrl: String
)