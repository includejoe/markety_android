package org.includejoe.markety.feature_user.domain.repository

import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_user.data.remote.dto.UserDTO

interface UserRepository {
    suspend fun getLoggedInUser(authHeader: String): UserDTO

    suspend fun getUser(authHeader: String, username: String): UserDTO

    suspend fun getUserPosts(authHeader: String, username: String): List<PostDTO>

    suspend fun updateUser(
        bio: String? = null,
        busCategory: String? = null,
        busName: String? = null,
        busWebsite: String? = null,
        coverImage: String? = null,
        dob: String? = null,
        firstName: String? = null,
        gender: String? = null,
        lastName: String? = null,
        location: String? = null,
        phone: String? = null,
        profileImage: String? = null,
    ): UserDTO

    suspend fun deleteUser(): Unit

}