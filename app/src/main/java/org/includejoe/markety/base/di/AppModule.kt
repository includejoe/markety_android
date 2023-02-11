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
import org.includejoe.markety.base.domain.repository.UserPreferencesRepository
import org.includejoe.markety.base.util.Constants
import org.includejoe.markety.base.util.TokenManager
import org.includejoe.markety.base.util.validators.*
import org.includejoe.markety.feature_authentication.domain.use_case.*
import org.includejoe.markety.feature_comment.domain.use_case.*
import retrofit2.Retrofit
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
}