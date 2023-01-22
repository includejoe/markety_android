package org.includejoe.markety.feature_post.domain.model



data class CreatePost(
    val category: String,
    val condition: String,
    val description: String,
    val image1: String,
    val image2: String,
    val image3: String,
    val name: String,
    val price: Float,
    val user: String
)