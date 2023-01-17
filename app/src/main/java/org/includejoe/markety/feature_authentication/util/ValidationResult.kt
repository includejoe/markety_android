package org.includejoe.markety.feature_authentication.util

import org.includejoe.markety.base.util.UIText


data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UIText? = null
)