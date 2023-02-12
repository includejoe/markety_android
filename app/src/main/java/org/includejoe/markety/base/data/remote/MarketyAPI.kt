package org.includejoe.markety.base.data.remote

import org.includejoe.markety.feature_authentication.data.remote.dto.CheckUsernameDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.domain.model.Register
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.feature_comment.domain.models.Comment
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.domain.model.CreatePost
import org.includejoe.markety.feature_user.data.remote.dto.UserDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MarketyAPI  {

    // AUTHENTICATION
    @POST("auth/login/")
     suspend fun login(@Body body: Login): LoginDTO

    @POST("auth/register/")
    suspend fun register(@Body body: Register): RegisterDTO

    @GET("user/check/{username}/")
    suspend fun checkUsername(
        @Path("username") username: String
    ): CheckUsernameDTO

    // USER
    @GET("user/")
    suspend fun getLoggedInUser(
        @Header("Authorization") authHeader: String
    ): UserDTO

    @GET("user/detail/{username}/")
    suspend fun getUser(
        @Header("Authorization") authHeader: String,
        @Path("username") username: String
    ): UserDTO



    // POSTS
    @GET("posts/user/{username}/")
    suspend fun getUserPosts(
        @Header("Authorization") authHeader: String,
        @Path("username") username: String
    ): List<PostDTO>

    @GET("posts/")
    suspend fun getPosts(
        @Header("Authorization") authHeader: String
    ): List<PostDTO>

    @POST("posts/create/")
    suspend fun createPost(
        @Body body: CreatePost,
        @Header("Authorization") authHeader: String
    ): PostDTO

    @GET("posts/detail/{postId}/")
    suspend fun getPost(
        @Path("postId") postId: String,
        @Header("Authorization") authHeader: String
    ): PostDTO


    // COMMENTS
    @POST("comments/create/")
    suspend fun createComment(
        @Header("Authorization") authHeader: String,
        @Body body: Comment
    ): CommentDTO

    @PATCH("comments/reply/{commentId}/")
    suspend fun replyComment(
        @Header("Authorization") authHeader: String,
        @Path("commentId") commentId: String,
        @Body body: Comment
    ): CommentDTO

    @PATCH("comments/like/{commentId}/")
    suspend fun likeComment(
        @Header("Authorization") authHeader: String,
        @Path("commentId") commentId: String,
    ): CommentDTO

    @GET("comments/{postId}/")
    suspend fun getPostComments(
        @Header("Authorization") authHeader: String,
        @Path("postId") postId: String,
    ): List<CommentDTO>

    @DELETE("comments/detail/{commentId}/")
    suspend fun deleteComment(
        @Header("Authorization") authHeader: String,
        @Path("commentId") commentId: String,
    )
}