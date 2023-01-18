package org.includejoe.markety.base.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.includejoe.markety.base.data.remote.MarketyAPI
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
    fun provideMarketyAPI(): MarketyAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarketyAPI::class.java)
    }

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