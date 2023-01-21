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
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.TokenManager
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
    ): SharedPreferences = context.getSharedPreferences(Constants.ENCRYPTED_SHARED_PREFS, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideTokenManager(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): TokenManager =
        TokenManager(context = context, sharedPreferences = sharedPreferences)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)


    @Provides
    @Singleton
    fun provideMarketyAPI(retrofit: Retrofit.Builder): MarketyAPI =
        retrofit
            .build()
            .create(MarketyAPI::class.java)


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