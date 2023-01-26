package org.includejoe.markety.feature_authentication.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import org.includejoe.markety.R

sealed class InputType(
    val label: Int,
    val icon: ImageVector,
){
    object Username: InputType(
        label = R.string.username_label,
        icon = Icons.Default.Person,
    )

    object Password: InputType(
        label = R.string.password_label,
        icon = Icons.Default.Lock,
    )

    object Email: InputType(
        label = R.string.email_label,
        icon = Icons.Default.Email,
    )

    object FirstName: InputType(
        label = R.string.firstname_label,
        icon = Icons.Default.Person,
    )

    object LastName: InputType(
        label = R.string.lastname_label,
        icon = Icons.Default.Person,
    )

    object Dob: InputType(
        label = R.string.dob_label,
        icon = Icons.Default.DateRange,
    )

    object Phone: InputType(
        label = R.string.phone_label,
        icon = Icons.Default.Phone,
    )

    object Location: InputType(
        label = R.string.location_label,
        icon = Icons.Default.LocationOn,
    )

    object ConfirmPassword: InputType(
        label = R.string.confirm_password_label,
        icon = Icons.Default.Lock,
    )

    object Gender: InputType(
        label = R.string.gender_label,
        icon = Icons.Default.Person,
    )

    object BusName: InputType(
        label = R.string.bus_name_label,
        icon = Icons.Default.Business,
    )

    object BusCategory: InputType(
        label = R.string.bus_category_label,
        icon = Icons.Default.Category,
    )
}
