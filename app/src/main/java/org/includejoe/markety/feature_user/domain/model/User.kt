package org.includejoe.markety.feature_user.domain.model

import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO

data class User(
    val bio: String?,
    val blocked_users: List<String>,
    val busCategory: String?,
    val busName: String?,
    val busWebsite: String?,
    val coverImage: String?,
    val createdAt: String,
    val dob: String,
    val email: String,
    val firstName: String,
    val followers: List<String>,
    val gender: String,
    val id: String,
    val isActive: Boolean,
    val isVendor: Boolean,
    val isVerified: Boolean,
    val lastName: String,
    val location: String,
    val phone: String,
    val posts: List<String>,
    val profileImage: String?,
    val tokens: TokensDTO,
    val updated_at: String,
    val username: String
)
