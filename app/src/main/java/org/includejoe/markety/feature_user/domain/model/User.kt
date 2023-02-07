package org.includejoe.markety.feature_user.domain.model

data class User(
    val bio: String? = "",
    val busCategory: String? = null,
    val busName: String? = null,
    val busWebsite: String? = null,
    val coverImage: String? = null,
    val createdAt: String? = null,
    val dob: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val followers: List<String>? = null,
    val following: List<String>? = null,
    val gender: String? = null,
    val id: String? = null,
    val isActive: Boolean? = null,
    val isVendor: Boolean? = null,
    val isVerified: Boolean? = null,
    val lastName: String? = null,
    val location: String? = null,
    val phone: String? = null,
    val posts: List<String>? = null,
    val profileImage: String? = null,
    var username: String? = null
)
