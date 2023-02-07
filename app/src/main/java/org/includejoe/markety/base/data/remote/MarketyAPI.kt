package org.includejoe.markety.base.data.remote

import org.includejoe.markety.feature_authentication.data.remote.dto.CheckUsernameDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.Login
import org.includejoe.markety.feature_authentication.domain.model.Register
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.domain.model.CreatePost
import org.includejoe.markety.feature_user.data.remote.dto.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MarketyAPI  {

    // AUTHENTICATION
    @POST("auth/login/")
     suspend fun login(@Body body: Login): LoginDTO

    @POST("auth/register/")
    suspend fun register(@Body body: Register): RegisterDTO

    @GET("user/check/{username}")
    suspend fun checkUsername(@Path("username") username: String): CheckUsernameDTO

    // USER
    @GET("user/")
    suspend fun getUser(
        @Header("Authorization") authHeader: String
    ): UserDTO



    // POSTS
    @GET("posts/user/{username}")
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

    @GET("posts/detail/{postId}")
    suspend fun getPost(
        @Path("postId") postId: String,
        @Header("Authorization") authHeader: String
    ): PostDTO

}