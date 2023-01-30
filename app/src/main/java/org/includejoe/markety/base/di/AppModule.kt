package org.includejoe.markety.base.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.includejoe.markety.base.data.remote.GooglePlacesAPI
import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.base.data.repository.GooglePlacesRepository
import org.includejoe.markety.base.domain.use_cases.GetGooglePlacesPredictions
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.data.repository.AuthenticationRepositoryImpl
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import org.includejoe.markety.feature_authentication.domain.use_case.*
import org.includejoe.markety.feature_authentication.util.validators.*
//import org.includejoe.markety.feature_user.data.repository.UserPreferencesRepositoryImpl
//import org.includejoe.markety.feature_user.domain.UserPreferencesRepository
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "org.includejoe.markety.user_preferences"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

//    @Binds
//    @Singleton
//    abstract fun bindUserPreferencesRepository(
//        userPreferencesRepositoryImpl: UserPreferencesRepositoryImpl
//    ): UserPreferencesRepository

    companion object {
        @Provides
        @Singleton
        fun provideUserDataStorePreferences(
            @ApplicationContext context: Context
        ): DataStore<Preferences> = context.userDataStore

        @Provides
        @Singleton
        fun provideEncryptedSharedPrefs(
            @ApplicationContext context: Context
        ): SharedPreferences =
            context.getSharedPreferences(Constants.ENCRYPTED_SHARED_PREFS, Context.MODE_PRIVATE)

        @Provides
        @Singleton
        fun provideTokenManager(
            @ApplicationContext context: Context,
            encryptedSharedPrefs: SharedPreferences
        ): TokenManager =
            TokenManager(context = context, encryptedSharedPrefs = encryptedSharedPrefs)

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

        @Provides
        @Singleton
        fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())

        // APIs
        @Provides
        @Singleton
        fun provideMarketyAPI(retrofit: Retrofit.Builder): MarketyAPI =
            retrofit
                .baseUrl(Constants.MARKETY_BASE_URL)
                .build()
                .create(MarketyAPI::class.java)

        @Provides
        @Singleton
        fun provideGooglePlacesAPI(retrofit: Builder): GooglePlacesAPI =
            retrofit
                .baseUrl(GooglePlacesAPI.BASE_URL)
                .build()
                .create(GooglePlacesAPI::class.java)


        // Repositories
        @Provides
        @Singleton
        fun provideAuthenticationRepository(api: MarketyAPI): AuthenticationRepository {
            return AuthenticationRepositoryImpl(api)
        }

        @Provides
        @Singleton
        fun provideGooglePlacesRepository(api: GooglePlacesAPI): GooglePlacesRepository {
            return GooglePlacesRepository(api)
        }

        // Use Cases
        @Provides
        @Singleton
        fun provideAuthenticationUseCases(
            repository: AuthenticationRepository,
            tokenManager: TokenManager
        ) = AuthenticationUseCases(
            login = LoginUseCase(repository = repository),
            register = RegisterUseCase(repository = repository),
            checkUsername = CheckUsernameUseCase(repository = repository),
            getNewAccessTokenUseCase = GetNewAccessTokenUseCase(
                repository = repository,
                tokenManager = tokenManager
            )
        )

        @Provides
        @Singleton
        fun provideGetGooglePlacesPredictions(
            repository: GooglePlacesRepository
        ) = GetGooglePlacesPredictions(repository = repository)
    }
}