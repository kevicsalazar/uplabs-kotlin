package com.kevicsalazar.uplabs.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
class Maker(
        val id: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("avatar_url") val avatarUrl: String
)