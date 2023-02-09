package org.includejoe.markety.feature_post.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO

@Composable
fun Actions(
    post: PostDTO? = null,
    comment: CommentDTO? = null,
    iconSize: Dp,
) {
    var showCommentDialog by remember { mutableStateOf(false) }
    fun likeCount(): String {
        return if(post !== null) {
            post.likes.size.toString()
        } else {
            comment?.likes?.size.toString()
        }
    }

    fun commentCount(): String {
        return if(post !== null) {
            post.comments.size.toString()
        } else {
            comment?.replies?.size.toString()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {

                        },
                    contentDescription = "heart icon",
                    tint = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = likeCount(),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Comment,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { showCommentDialog = true },
                    contentDescription = "comment icon",
                    tint = MaterialTheme.colors.onBackground,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = commentCount(),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }
        }
        if(post !== null) {
            Icon(
                imageVector = Icons.Outlined.AddShoppingCart,
                modifier = Modifier.size(iconSize),
                contentDescription = "save icon",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }

    if(showCommentDialog) {
        CommentDialog(cancel = { showCommentDialog = false }) {

        }
    }
}