package org.includejoe.markety.feature_post.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PostDTO(
    val category: String,
    val comments: List<String>,
    val condition: String,
    @SerializedName("created_at")
    val createdAt: String,
    val description: String?,
    val id: String,
    val image1: String,
    val image2: String,
    val image3: String,
    @SerializedName("is_sold")
    val isSold: Boolean,
    val likes: List<String>,
    val name: String,
    val price: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val user: String
)