package org.includejoe.markety.base.data.repository

import org.includejoe.markety.base.data.remote.GooglePlacesAPI
import org.includejoe.markety.base.data.remote.dto.GooglePredictionsDTO
import org.includejoe.markety.base.util.Response
import javax.inject.Inject

class GooglePlacesRepository @Inject constructor(
    private val api: GooglePlacesAPI
) {
    suspend fun getPredictions(input: String): GooglePredictionsDTO {
        return api.getPredictions(input = input)
    }
}