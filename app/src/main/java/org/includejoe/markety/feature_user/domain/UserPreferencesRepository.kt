package org.includejoe.markety.feature_user.domain

import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO
import org.includejoe.markety.feature_user.domain.model.User

interface UserPreferencesRepository {
    suspend fun setUserDetails(
        bio: String?,
        blocked_users: List<String>,
        busCategory: String?,
        busName: String?,
        busWebsite: String?,
        coverImage: String?,
        createdAt: String,
        dob: String,
        email: String,
        firstName: String,
        followers: List<String>,
        gender: String,
        id: String,
        isActive: Boolean,
        isVendor: Boolean,
        isVerified: Boolean,
        lastName: String,
        location: String,
        phone: String,
        posts: List<String>,
        profileImage: String?,
        tokens: TokensDTO,
        updated_at: String,
        username: String
    )

    suspend fun getUserDetails(): Result<User>
}