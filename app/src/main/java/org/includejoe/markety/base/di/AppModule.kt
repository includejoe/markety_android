package org.includejoe.markety.base.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.includejoe.markety.base.data.remote.MarketyAPI
import org.includejoe.markety.base.domain.AppSharedPreferences
import org.includejoe.markety.base.domain.AuthenticationInterceptor
import org.includejoe.markety.base.domain.SessionManager
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.feature_authentication.data.repository.AuthenticationRepositoryImpl
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import org.includejoe.markety.feature_authentication.domain.use_case.AuthenticationUseCases
import org.includejoe.markety.feature_authentication.domain.use_case.LoginUseCase
import org.includejoe.markety.feature_authentication.util.validators.FormValidators
import org.includejoe.markety.feature_authentication.util.validators.ValidatePassword
import org.includejoe.markety.feature_authentication.util.validators.ValidateUsername
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences =
        context.getSharedPreferences(Constants.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAppSharedPreferences(
        sharedPreferences: SharedPreferences
    ) = AppSharedPreferences(sharedPreferences)

    @Provides
    @Singleton
    fun provideSessionManager(
        appSharedPreferences: AppSharedPreferences,
//        repository: AuthenticationRepository
    ): SessionManager = SessionManager(
        preferences = appSharedPreferences,
//        authRepository = repository
    )

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(
        sessionManager: SessionManager,
//        authRepository: AuthenticationRepository
    ): AuthenticationInterceptor = AuthenticationInterceptor(
        sessionManager = sessionManager,
//        authRepository = authRepository
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthenticationInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)

    @Provides
    @Singleton
    fun provideMarketyAPI(retrofit: Retrofit.Builder): MarketyAPI =
        retrofit
            .build()
            .create(MarketyAPI::class.java)

//    @Provides
//    @Singleton
//    fun provideMarketyAPI(): MarketyAPI {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(MarketyAPI::class.java)
//    }


    @Provides
    @Singleton
    fun provideAuthenticationRepository(api: MarketyAPI): AuthenticationRepository {
        return AuthenticationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAuthenticationUseCases(repository: AuthenticationRepository) = AuthenticationUseCases(
        login = LoginUseCase(repository = repository)
    )

    @Provides
    @Singleton
    fun provideFormValidators() = FormValidators (
        username = ValidateUsername(),
        password = ValidatePassword()
    )
}