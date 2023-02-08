package org.includejoe.markety.base.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.includejoe.markety.base.BaseApplication
import org.includejoe.markety.base.data.remote.GooglePlacesAPI
import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.base.data.repository.GooglePlacesRepository
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import org.includejoe.markety.base.domain.use_cases.GetGooglePlacesPredictionsUseCase
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.data.repository.AuthenticationRepositoryImpl
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import org.includejoe.markety.feature_authentication.domain.use_case.*
import org.includejoe.markety.feature_authentication.util.validators.*
import org.includejoe.markety.feature_comment.data.repository.CommentRepositoryImpl
import org.includejoe.markety.feature_comment.domain.repository.CommentRepository
import org.includejoe.markety.feature_comment.domain.use_case.*
import org.includejoe.markety.feature_post.data.PostsRepositoryImpl
import org.includejoe.markety.feature_post.domain.repository.PostRepository
import org.includejoe.markety.feature_post.domain.use_case.GetPostUseCase
import org.includejoe.markety.feature_post.domain.use_case.GetPostsUseCase
import org.includejoe.markety.feature_post.domain.use_case.PostUseCases
import org.includejoe.markety.feature_user.data.repository.UserRepositoryImpl
import org.includejoe.markety.feature_user.domain.repository.UserRepository
import org.includejoe.markety.feature_user.domain.use_case.GetLoggedInUserUseCase
import org.includejoe.markety.feature_user.domain.use_case.GetUserPosts
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val Context.userPreferences: DataStore<Preferences> by preferencesDataStore(
    name = Constants.DATASTORE_USER_PREFERENCES
)

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Base Application
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    // User Preferences DataStore
    @Provides
    @Singleton
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return context.userPreferences
    }

    // User Preferences Repository
    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        userPreferences: DataStore<Preferences>
    ): UserPreferencesRepository = UserPreferencesRepository(
        userPreferences = userPreferences
    )

    // Encrypted Shared Preferences
    @Provides
    @Singleton
    fun provideEncryptedSharedPrefs(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences(Constants.ENCRYPTED_SHARED_PREFS, Context.MODE_PRIVATE)

    // Token Manager
    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context,
        encryptedSharedPrefs: SharedPreferences,
        baseApp: BaseApplication
    ): TokenManager =
        TokenManager(
            context = context,
            encryptedSharedPrefs = encryptedSharedPrefs,
            baseApp = baseApp
        )

    // Form Validators
    @Provides
    @Singleton
    fun provideFormValidators() = FormValidators(
        email = ValidateEmail(),
        username = ValidateUsername(),
        firstName = ValidateFirstName(),
        lastName = ValidateLastName(),
        gender = ValidateGender(),
        dob = ValidateDob(),
        phone = ValidatePhone(),
        password = ValidatePassword(),
        confirmPassword = ValidateConfirmPassword(),
        location = ValidateLocation(),
        busCategory = ValidateBusCategory(),
        busName = ValidateBusName()
    )

    // Retrofit
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())

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
    fun provideGooglePlacesAPI(retrofit: Builder): GooglePlacesAPI =
        retrofit
            .baseUrl(GooglePlacesAPI.BASE_URL)
            .build()
            .create(GooglePlacesAPI::class.java)


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


    @Provides
    @Singleton
    fun provideUserRepository(api: MarketyAPI): UserRepository {
        return UserRepositoryImpl(api)
    }

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
        getLoggedInUserUseCase = GetLoggedInUserUseCase(repository),
        getUserPosts = GetUserPosts(repository)
    )

    // Post Repository
    @Provides
    @Singleton
    fun providePostRepository(
        api: MarketyAPI
    ): PostRepository {
        return PostsRepositoryImpl(api)
    }

    // Post Use Cases
    @Provides
    @Singleton
    fun providePostUseCases(
        repository: PostRepository
    ) = PostUseCases(
        getPosts = GetPostsUseCase(repository),
        getPost = GetPostUseCase(repository)
    )

    // Comment Repository
    @Provides
    @Singleton
    fun provideCommentRepository(
        api: MarketyAPI
    ): CommentRepository {
        return CommentRepositoryImpl(api)
    }

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