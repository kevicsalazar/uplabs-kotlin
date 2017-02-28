package com.kevicsalazar.uplabs.domain.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
class Submitter(
        val id: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("avatar_url") val avatarUrl: String
)