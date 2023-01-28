package org.includejoe.markety.feature_authentication.domain.use_case

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.util.Response
import org.includejoe.markety.feature_authentication.data.remote.dto.RegisterDTO
import org.includejoe.markety.feature_authentication.domain.model.RegistrationError
import org.includejoe.markety.feature_authentication.domain.repository.AuthenticationRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    private val gson = Gson()
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
            val errorString = e.response()?.errorBody()?.string()
            val errorResponse: RegistrationError = gson.fromJson(errorString, RegistrationError::class.java)

            if (errorResponse.email !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.email.first()))
            }else if (errorResponse.username !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.username.first()))

            }else if (errorResponse.first_name !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.first_name.first()))

            } else if (errorResponse.last_name !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.last_name.first()))

            } else if (errorResponse.dob !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.dob.first()))

            } else if (errorResponse.password !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.password.first()))

            } else if (errorResponse.gender !== null) {
                emit(Response.Error<RegisterDTO>(errorResponse.gender.first()))

            } else {
                emit(Response.Error<RegisterDTO>(R.string.unexpected_error))
            }
        } catch(e: IOException) {
            emit(Response.Error<RegisterDTO>(R.string.internet_error))
        }
    }
}