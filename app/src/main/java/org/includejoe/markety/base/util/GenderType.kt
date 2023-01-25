package org.includejoe.markety.base.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.includejoe.markety.R

enum class GenderType(
    val label: Int,
    val icon: ImageVector
) {
    MALE(
        label = R.string.male,
        icon = Icons.Default.Male,
    ),

    FEMALE(
        label = R.string.female,
        icon = Icons.Default.Female,
    ),

    OTHER(
        label = R.string.other,
        icon = Icons.Default.Person,
    )
}