package org.includejoe.markety.base.data.remote.dto

import org.includejoe.markety.base.domain.model.GooglePrediction

data class GooglePredictionsDTO(
    val predictions: ArrayList<GooglePrediction>
)
