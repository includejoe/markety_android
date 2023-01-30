package org.includejoe.markety.feature_user.data.remote.dto

import com.google.gson.annotations.SerializedName
import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO
data class UserDTO(
    val bio: String?,
    val blocked_users: List<String>,
    @SerializedName("bus_category")
    val busCategory: String?,
    @SerializedName("bus_name")
    val busName: String?,
    @SerializedName("bus_website")
    val busWebsite: String?,
    @SerializedName("cover_image")
    val coverImage: String?,
    @SerializedName("created_at")
    val createdAt: String,
    val dob: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    val followers: List<String>,
    val gender: String,
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_vendor")
    val isVendor: Boolean,
    @SerializedName("is_verified")
    val isVerified: Boolean,
    @SerializedName("last_name")
    val lastName: String,
    val location: String,
    val phone: String,
    val posts: List<String>,
    @SerializedName("profile_image")
    val profileImage: String?,
    val tokens: TokensDTO,
    val updated_at: String,
    val username: String
)