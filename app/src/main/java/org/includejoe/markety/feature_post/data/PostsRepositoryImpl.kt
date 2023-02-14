package org.includejoe.markety.feature_post.data

import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.domain.repository.PostRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val api: MarketyAPI
): PostRepository {
    override suspend fun createPost(
        category: String,
        condition: String,
        image1: String,
        image2: String,
        image3: String,
        isSold: Boolean,
        name: String,
        price: String,
        user: String
    ): PostDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getPost(authHeader: String, postId: String): PostDTO {
        return api.getPost(authHeader = authHeader, postId = postId)
    }

    override suspend fun getPosts(authHeader: String): List<PostDTO> {
        return api.getPosts(authHeader)
    }

    override suspend fun likePost(authHeader: String, postId: String): PostDTO {
        return api.likePost(authHeader = authHeader, postId = postId)
    }
}