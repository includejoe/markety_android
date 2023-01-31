package org.includejoe.markety.base.domain.use_cases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.includejoe.markety.R
import org.includejoe.markety.base.data.remote.dto.GooglePredictionsDTO
import org.includejoe.markety.base.data.repository.GooglePlacesRepository
import org.includejoe.markety.base.util.Response
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGooglePlacesPredictionsUseCase @Inject constructor(
    private val repository: GooglePlacesRepository
) {
    operator fun invoke(input: String): Flow<Response<GooglePredictionsDTO>> = flow {
        try{
            emit(Response.Loading<GooglePredictionsDTO>())
            val data = repository.getPredictions(input)
            emit(Response.Success<GooglePredictionsDTO>(data))
        }catch (e: HttpException){
            emit(Response.Error<GooglePredictionsDTO>(R.string.unexpected_error))
        } catch(e: IOException) {
            emit(Response.Error<GooglePredictionsDTO>(R.string.internet_error))
        }
    }
}