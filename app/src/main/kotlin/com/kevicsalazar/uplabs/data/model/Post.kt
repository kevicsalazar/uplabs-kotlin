package com.kevicsalazar.uplabs.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
data class Post(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("points") val points: Int,
        @SerializedName("link_url") val linkUrl: String,
        @SerializedName("background_color") val colorHex: String,
        @SerializedName("description") val description: String?,
        @SerializedName("maker_name") val makerName: String?,
        @SerializedName("serialized_submitter") val submitter: Submitter?,
        @SerializedName("preview_url") val previewUrl: String
)