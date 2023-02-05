package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_user.util.UserViewModelState

@Composable
fun Details(
    isVendor: Boolean = false,
    state: State<UserViewModelState>
) {
    val iconSize = 15.dp

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
        // First & Last Name or Business Name
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
        ) {
            if (isVendor) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = state.value.data?.busName ?: "",
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_is_vendor),
                        modifier = Modifier.size(12.dp),
                        contentDescription = "location icon",
                    )
                }
            } else {
                Text(
                    text = "${state.value.data?.firstName} ${state.value.data?.lastName}",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1
                )
            }
        }

        // Username
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "@${state.value.data?.username}",
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                fontSize = 12.sp,
                style = MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

        // Bio
        if (state.value.data?.bio !== null) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = state.value.data?.bio!!,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // Location
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    modifier = Modifier.size(iconSize),
                    contentDescription = "location icon",
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = state.value.data?.location ?: "",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))

            // Category
            if(isVendor) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Category,
                        modifier = Modifier.size(iconSize),
                        contentDescription = "category icon",
                        tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = state.value.data?.busCategory ?: "",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // Business Website
            if(isVendor && state.value.data?.busWebsite !== null) {
                Row(
                    modifier = Modifier.wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Link,
                        modifier = Modifier.size(iconSize),
                        contentDescription = "website icon",
                        tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    BusWebsiteLink(link = state.value.data?.busWebsite!!)
                }
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            }

            // Joined Date
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    modifier = Modifier.size(iconSize),
                    contentDescription = "joined icon",
                    tint = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.width(3.dp))
                JoinedDate(dateString = state.value.data?.createdAt!!)
            }
        }

    }
}