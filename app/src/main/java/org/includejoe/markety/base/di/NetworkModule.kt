package org.includejoe.markety.base.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.includejoe.markety.base.data.remote.GooglePlacesAPI
import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.base.data.repository.GooglePlacesRepository
import org.includejoe.markety.base.domain.use_cases.GetGooglePlacesPredictionsUseCase
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.data.repository.AuthenticationRepositoryImpl
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import org.includejoe.markety.feature_authentication.domain.use_case.AuthenticationUseCases
import org.includejoe.markety.feature_authentication.domain.use_case.CheckUsernameUseCase
import org.includejoe.markety.feature_authentication.domain.use_case.LoginUseCase
import org.includejoe.markety.feature_authentication.domain.use_case.RegisterUseCase
import org.includejoe.markety.feature_comment.data.repository.CommentRepositoryImpl
import org.includejoe.markety.feature_comment.domain.repository.CommentRepository
import org.includejoe.markety.feature_comment.domain.use_case.*
import org.includejoe.markety.feature_post.data.PostsRepositoryImpl
import org.includejoe.markety.feature_post.domain.repository.PostRepository
import org.includejoe.markety.feature_post.domain.use_case.GetPostUseCase
import org.includejoe.markety.feature_post.domain.use_case.GetPostsUseCase
import org.includejoe.markety.feature_post.domain.use_case.LikePostUseCase
import org.includejoe.markety.feature_post.domain.use_case.PostUseCases
import org.includejoe.markety.feature_user.data.repository.UserRepositoryImpl
import org.includejoe.markety.feature_user.domain.repository.UserRepository
import org.includejoe.markety.feature_user.domain.use_case.GetLoggedInUserUseCase
import org.includejoe.markety.feature_user.domain.use_case.GetUserPosts
import org.includejoe.markety.feature_user.domain.use_case.GetUserUseCase
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /* API SERVICES */
    // Markety API
    @Provides
    @Singleton
    fun provideMarketyAPI(retrofit: Retrofit.Builder): MarketyAPI =
        retrofit
            .baseUrl(Constants.MARKETY_BASE_URL)
            .build()
            .create(MarketyAPI::class.java)

    // Google Places API
    @Provides
    @Singleton
    fun provideGooglePlacesAPI(retrofit: Retrofit.Builder): GooglePlacesAPI =
        retrofit
            .baseUrl(GooglePlacesAPI.BASE_URL)
            .build()
            .create(GooglePlacesAPI::class.java)

    /* REPOSITORIES */
    // Authentication Repository
    @Provides
    @Singleton
    fun provideAuthenticationRepository(api: MarketyAPI): AuthenticationRepository {
        return AuthenticationRepositoryImpl(api)
    }

    // Google Places Repository
    @Provides
    @Singleton
    fun provideGooglePlacesRepository(api: GooglePlacesAPI): GooglePlacesRepository {
        return GooglePlacesRepository(api)
    }

    // User Repository
    @Provides
    @Singleton
    fun provideUserRepository(api: MarketyAPI): UserRepository {
        return UserRepositoryImpl(api)
    }

    // Post Repository
    @Provides
    @Singleton
    fun providePostRepository(
        api: MarketyAPI
    ): PostRepository {
        return PostsRepositoryImpl(api)
    }

    // Comment Repository
    @Provides
    @Singleton
    fun provideCommentRepository(
        api: MarketyAPI
    ): CommentRepository {
        return CommentRepositoryImpl(api)
    }


    /* USE CASES */
    // Authentication Use Cases
    @Provides
    @Singleton
    fun provideAuthenticationUseCases(
        repository: AuthenticationRepository,
    ) = AuthenticationUseCases(
        login = LoginUseCase(repository),
        register = RegisterUseCase(repository),
        checkUsername = CheckUsernameUseCase(repository)
    )

    // GetGooglePlacesPredictions Use Case
    @Provides
    @Singleton
    fun provideGetGooglePlacesPredictionsUseCase(
        repository: GooglePlacesRepository
    ) = GetGooglePlacesPredictionsUseCase(repository)

    // User Use Cases
    @Provides
    @Singleton
    fun provideUserUseCases(
        repository: UserRepository
    ) = UserUseCases(
        getLoggedInUser = GetLoggedInUserUseCase(repository),
        getUser = GetUserUseCase(repository),
        getUserPosts = GetUserPosts(repository)
    )

    // Post Use Cases
    @Provides
    @Singleton
    fun providePostUseCases(
        repository: PostRepository
    ) = PostUseCases(
        getPosts = GetPostsUseCase(repository),
        getPost = GetPostUseCase(repository),
        likePost = LikePostUseCase(repository)
    )

    // Comment Use Cases
    @Provides
    @Singleton
    fun provideCommentUseCases(
        repository: CommentRepository
    ) = CommentUseCases(
        createComment = CreateCommentUseCase(repository),
        replyComment = ReplyCommentUseCase(repository),
        likeComment = LikeCommentUseCase(repository),
        getPostComments = GetPostCommentsUseCase(repository),
        deleteComment = DeleteCommentUseCase(repository)
    )
}