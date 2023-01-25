package org.includejoe.markety.base.domain.model

data class GooglePrediction(
    val description: String,
    val terms: List<GooglePredictionTerm>
)
