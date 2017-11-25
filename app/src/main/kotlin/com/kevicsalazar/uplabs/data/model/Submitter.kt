package com.kevicsalazar.uplabs.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
@Entity
data class Submitter(
        @PrimaryKey
        @SerializedName("id")
        val id: String,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("avatar_url")
        val avatarUrl: String
)