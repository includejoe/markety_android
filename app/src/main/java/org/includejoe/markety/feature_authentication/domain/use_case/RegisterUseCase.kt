package org.includejoe.markety.feature_authentication.domain.use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_authentication.data.remote.dto.LoginDTO
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
        phone: String,
        gender: String,
        dob: String,
        location: String,
        isVendor: Boolean,
        busName: String?,
        busCategory: String?
    ): Flow<Response<RegisterDTO>> = flow {
        try {
            emit(Response.Loading<RegisterDTO>())
            val data = repository.register(
                email = email,
                password = password,
                username = username,
                firstName = firstName,
                lastName = lastName,
                phone = phone,
                gender = gender,
                dob = dob,
                location = location,
                isVendor = isVendor,
                busName = busName,
                busCategory = busCategory
            )
            emit(Response.Success<RegisterDTO>(data))
        } catch (e: HttpException) {
            Log.d("register_error", e.toString())
            when(e.code()) {
                400 -> {
                    emit(Response.Error<RegisterDTO>(R.string.invalid_credentials))
                }
                else -> {
                    emit(Response.Error<RegisterDTO>(R.string.unexpected_error))
                }
            }
        } catch(e: IOException) {
            emit(Response.Error<RegisterDTO>(R.string.internet_error))
        }
    }
}