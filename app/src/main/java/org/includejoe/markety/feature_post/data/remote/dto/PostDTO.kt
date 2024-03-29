package org.includejoe.markety.feature_post.data.remote.dto

import com.google.gson.annotations.SerializedName
import org.includejoe.markety.base.domain.model.UserInfo

data class PostDTO(
    val category: String,
    val comments: List<String>,
    @SerializedName("is_new")
    val isNew: Boolean,
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
    val user: UserInfo
)
