package com.kevicsalazar.uplabs.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
class Submitter(
        @SerializedName("id") val id: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("avatar_url") val avatarUrl: String
)