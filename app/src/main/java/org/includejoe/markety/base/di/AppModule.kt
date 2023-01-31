package org.includejoe.markety.base.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.includejoe.markety.base.data.remote.GooglePlacesAPI
import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.base.data.repository.GooglePlacesRepository
import org.includejoe.markety.base.domain.use_cases.GetGooglePlacesPredictionsUseCase
import org.includejoe.markety.base.domain.AppState
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.CryptoManager
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.feature_authentication.data.repository.AuthenticationRepositoryImpl
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import org.includejoe.markety.feature_authentication.domain.use_case.*
import org.includejoe.markety.feature_authentication.util.validators.*
import org.includejoe.markety.feature_user.data.repository.UserRepositoryImpl
import org.includejoe.markety.feature_user.domain.model.User
import org.includejoe.markety.feature_user.domain.model.UserSerializer
import org.includejoe.markety.feature_user.domain.repository.UserRepository
import org.includejoe.markety.feature_user.domain.use_case.GetLoggedInUserUseCase
import org.includejoe.markety.feature_user.domain.use_case.UserUseCases
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Application State
    @Provides
    @Singleton
    fun provideAppState(): State<AppState> = mutableStateOf(AppState())

    // Crypto Manager
    @Provides
    @Singleton
    fun provideCryptoManager(): CryptoManager = CryptoManager()

    // User Data Store
    @Provides
    @Singleton
    fun provideUserDataStore(
        @ApplicationContext context: Context,
        cryptoManager: CryptoManager
    ): DataStore<User> =
        DataStoreFactory.create(
            serializer = UserSerializer(cryptoManager),
            produceFile = {context.dataStoreFile(Constants.USER_DATASTORE_FILE_NAME)}
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
        appState: State<AppState>
    ): TokenManager =
        TokenManager(
            context = context,
            encryptedSharedPrefs = encryptedSharedPrefs,
            appState = appState
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
        tokenManager: TokenManager,
        repository: UserRepository
    ) = UserUseCases(
        getLoggedInUserUseCase = GetLoggedInUserUseCase(
            repository = repository
        )
    )

}