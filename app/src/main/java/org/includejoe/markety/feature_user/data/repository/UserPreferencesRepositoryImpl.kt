package org.includejoe.markety.feature_user.data.repository

import androidx.datastore.core.DataStore
import org.includejoe.markety.feature_authentication.data.remote.dto.TokensDTO
import org.includejoe.markety.feature_user.domain.UserPreferencesRepository
import org.includejoe.markety.feature_user.domain.model.User
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

//class UserPreferencesRepositoryImpl @Inject constructor(
//    private val userDataStorePreferences: DataStore<Preferences>
//): UserPreferencesRepository {
//    override suspend fun setUserDetails(
//        bio: String?,
//        blocked_users: List<String>,
//        busCategory: String?,
//        busName: String?,
//        busWebsite: String?,
//        coverImage: String?,
//        createdAt: String,
//        dob: String,
//        email: String,
//        firstName: String,
//        followers: List<String>,
//        gender: String,
//        id: String,
//        isActive: Boolean,
//        isVendor: Boolean,
//        isVerified: Boolean,
//        lastName: String,
//        location: String,
//        phone: String,
//        posts: List<String>,
//        profileImage: String?,
//        tokens: TokensDTO,
//        updated_at: String,
//        username: String
//    ) {
//        Result.runCatching {
//            userDataStorePreferences.edit { preferences ->
//                preferences[USER]
//            }
//        }
//    }
//
//    override suspend fun getUserDetails(): Result<User> {
//        return Result.runCatching {
//            val flow = userDataStorePreferences.data.catch {exception ->
//                if(exception is IOException) {
//                    emit(emptyPreferences())
//                } else {
//                    throw exception
//                }
//            }.map { preferences ->
//                preferences[]
//            }
//        }
//    }
//
//    private companion object {
//        val USER = stringPreferencesKey (
//            name = "user"
//        )
//    }
//}
