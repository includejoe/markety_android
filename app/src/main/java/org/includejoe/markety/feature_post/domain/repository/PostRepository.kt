package org.includejoe.markety.feature_post.domain.repository

import org.includejoe.markety.feature_post.data.remote.dto.PostDTO

interface PostRepository {
    suspend fun createPost(
        category: String,
        condition: String,
        image1: String,
        image2: String,
        image3: String,
        isSold: Boolean,
        name: String,
        price: String,
        user: String
    ): PostDTO

    suspend fun getPost(authHeader: String): PostDTO

    suspend fun getPosts(authHeader: String): List<PostDTO>
}