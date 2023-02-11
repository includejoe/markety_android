package org.includejoe.markety.feature_user.data.repository

import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_user.data.remote.dto.UserDTO
import org.includejoe.markety.feature_user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: MarketyAPI
): UserRepository {
    override suspend fun getLoggedInUser(authHeader: String): UserDTO {
        return api.getLoggedInUser(authHeader)
    }

    override suspend fun getUser(authHeader: String, username: String): UserDTO {
        return api.getUser(authHeader = authHeader, username = username)
    }

    override suspend fun getUserPosts(authHeader: String, username: String): List<PostDTO> {
        return api.getUserPosts(authHeader = authHeader, username = username)
    }

    override suspend fun updateUser(
        bio: String?,
        busCategory: String?,
        busName: String?,
        busWebsite: String?,
        coverImage: String?,
        dob: String?,
        firstName: String?,
        gender: String?,
        lastName: String?,
        location: String?,
        phone: String?,
        profileImage: String?
    ): UserDTO {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }
}