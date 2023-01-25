package org.includejoe.markety.base.data.remote

import org.includejoe.markety.base.data.remote.dto.GooglePredictionsDTO
import org.includejoe.markety.base.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesAPI {
    @GET("maps/api/place/autocomplete/json")
    suspend fun getPredictions(
        @Query("key") key: String = Constants.GOOGLE_MAPS_API_KEY,
        @Query("types") types: String = "address",
        @Query("input") input: String
    ): GooglePredictionsDTO

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/"
    }
}