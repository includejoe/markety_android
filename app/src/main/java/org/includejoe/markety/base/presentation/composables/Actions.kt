package org.includejoe.markety.base.presentation.composables

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.includejoe.markety.base.presentation.ActionsViewModel
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_comment.data.remote.dto.CommentDTO
import org.includejoe.markety.feature_comment.presentation.CommentViewModel
import org.includejoe.markety.feature_comment.presentation.composables.CommentDialog
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.presentation.PostDetailViewModel
import org.includejoe.markety.feature_user.presentation.LoggedInUserViewModel

@Composable
fun Actions(
    post: PostDTO? = null,
    comment: CommentDTO? = null,
    iconSize: Dp,
    viewModel: ActionsViewModel = hiltViewModel(),
    userVM: LoggedInUserViewModel = hiltViewModel(),
    postVM: PostDetailViewModel = hiltViewModel(),
    commentVM: CommentViewModel = hiltViewModel(),
) {
    var showCommentDialog by remember { mutableStateOf(false) }

    // LIKE
    var commentLikeCount by remember { mutableStateOf(comment?.likes?.size) }
    var postLikeCount by remember { mutableStateOf(post?.likes?.size) }

    var isPostLiked by remember {
        mutableStateOf(post?.likes?.contains(userVM.baseApp.userDetails.value?.id))
    }
    var isCommentLiked by remember {
        mutableStateOf(comment?.likes?.contains(userVM.baseApp.userDetails.value?.id))
    }

    fun likeCount(): String {
        return if(post !== null) {
            postLikeCount.toString()
        } else {
            commentLikeCount.toString()
        }
    }

    fun likeAction() {
        if (post != null) {
            postVM.likePost(post.id)
            isPostLiked = !isPostLiked!!
            postLikeCount = if(isPostLiked!!) {
                postLikeCount!! + 1
            } else {
                postLikeCount!! - 1
            }
        } else {
            commentVM.likeComment(comment!!.id)
            isCommentLiked = !isCommentLiked!!
            commentLikeCount = if (isCommentLiked!!) {
                commentLikeCount!! + 1
            } else {
                commentLikeCount!! - 1
            }
        }
    }

    fun isLiked(): Boolean {
        return isPostLiked ?: isCommentLiked!!
    }

    // COMMENT
    var postCommentCount by remember { mutableStateOf(post?.comments?.size) }
    var commentReplyCount by remember { mutableStateOf(comment?.replies?.size) }

    fun commentCount(): String {
        return if(post !== null) {
            postCommentCount.toString()
        } else {
            commentReplyCount.toString()
        }
    }

    fun incrementCommentCount() {
        if (commentVM.state.value.data != null) {
            if (post != null) {
                postCommentCount = postCommentCount!! + 1
            } else {
                commentReplyCount = commentReplyCount!! + 1
            }
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
            // LIKE
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isLiked()) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder,
                    modifier = Modifier
                        .size(iconSize)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            likeAction()
                        },
                    contentDescription = "heart icon",
                    tint = if (isLiked()) Color.Red
                    else MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = likeCount(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.md))

            // COMMENT
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
        if (post !== null) {
            Icon(
                imageVector = Icons.Outlined.AddShoppingCart,
                modifier = Modifier.size(iconSize),
                contentDescription = "save icon",
                tint = MaterialTheme.colors.onBackground
            )
        }
    }


    if(showCommentDialog) {
        CommentDialog(
            replyingTo = comment?.user?.username,
            postId = post?.id ?: comment!!.post,
            commentId = comment?.id,
            incrementCommentCount = {incrementCommentCount()}

        ) {
            showCommentDialog = false
        }
    }
}