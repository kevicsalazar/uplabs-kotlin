package com.kevicsalazar.uplabs.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Kevin.
 */
@Entity
data class Post(
        @PrimaryKey
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("points") val points: Int,
        @SerializedName("link_url") val linkUrl: String,
        @SerializedName("background_color") val colorHex: String,
        @SerializedName("description") val description: String?,
        @SerializedName("maker_name") val makerName: String?,
        @SerializedName("preview_url") val previewUrl: String
)