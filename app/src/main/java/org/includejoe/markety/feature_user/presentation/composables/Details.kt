package org.includejoe.markety.feature_user.presentation.composables

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.Name
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_user.domain.model.User
import org.includejoe.markety.feature_user.util.UserState

@Composable
fun Details(
    isVendor: Boolean = false,
    details: User
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
            Name(
                isVendor = isVendor,
                busName = details.busName,
                firstName = details.firstName!!,
                lastName = details.lastName!!
            )
        }

        // Username
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "@${details.username}",
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                style = MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))

        // Bio
        if (!details.bio.isNullOrEmpty()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = details.bio,
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
                    text = details.location ?: "",
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
                        text = details.busCategory ?: "",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            // Business Website
            if(isVendor && details.busWebsite !== null) {
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
                    BusWebsiteLink(link = details.busWebsite)
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
                JoinedDate(dateString = details.createdAt!!)
            }
        }

        // Followers, Following & Posts
        Row(modifier = Modifier.fillMaxWidth()) {
            Row() {
                Text(
                    text = details.following?.size.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.following),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))

            Row() {
                Text(
                    text = details.followers?.size.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.followers),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))

            Row() {
                Text(
                    text = details.posts?.size.toString(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = stringResource(id = R.string.posts),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}