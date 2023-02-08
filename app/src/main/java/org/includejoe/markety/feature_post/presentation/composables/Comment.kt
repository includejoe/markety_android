package org.includejoe.markety.feature_post.presentation.composables

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_post.presentation.PostDetailViewModel

@Composable
fun Comment(
    isDarkTheme: Boolean,
    // TODO: Remove PostDetailViewModel when CommentDTO is created
    viewModel: PostDetailViewModel = hiltViewModel()
//    comment: CommentDTO
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Avatar(isDarkTheme = isDarkTheme, modifier = Modifier.size(60.dp))
        Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
        Column(modifier = Modifier.weight(1f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Text(
                        text = "John Doe",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "@johnny",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
                    Text(
                        text = "4hrs ago",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onBackground
                    )
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

            Text(
                text = "This is a sample comment",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground
            )

            Actions(
                post = viewModel.state.value.post!!,
                iconSize = 15.dp,
                forPost = false
            )

        }
    }
}