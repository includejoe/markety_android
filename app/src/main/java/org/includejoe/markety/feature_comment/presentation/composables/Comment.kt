package org.includejoe.markety.feature_comment.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.composables.DateFormatter
import org.includejoe.markety.base.presentation.composables.Name
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.base.presentation.composables.Actions
import org.includejoe.markety.R

@Composable
fun Comment(
    isDarkTheme: Boolean,
    comment: CommentDTO,
    replyingTo: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(
            isDarkTheme = isDarkTheme,
            modifier = Modifier.size(60.dp),
            src = comment.user.profileImage
        )
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Name(
                        isVendor = comment.user.isVendor,
                        busName = comment.user.busName,
                        firstName = comment.user.firstName,
                        lastName = comment.user.lastName
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "@${comment.user.username}",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
                    DateFormatter(dateString = comment.createdAt, fontWeight = FontWeight.Normal)
                }

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "menu",
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {}
                )
            }

            if (!replyingTo.isNullOrEmpty()) {
                Text(
                    text = stringResource(id = R.string.replying_to, "@$replyingTo"),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                )
            }

            Text(
                text = comment.body,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )

            Actions(
                comment = comment,
                iconSize = 15.dp,
            )
        }
    }
}