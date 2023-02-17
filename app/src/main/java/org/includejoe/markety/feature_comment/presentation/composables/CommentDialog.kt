package org.includejoe.markety.feature_comment.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.CButton
import org.includejoe.markety.base.presentation.composables.Toast
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_comment.presentation.CommentViewModel

@Composable
fun CommentDialog(
    replyingTo: String? = null,
    commentId: String? = null,
    postId: String,
    viewModel: CommentViewModel = hiltViewModel(),
    incrementCommentCount: () -> Unit,
    cancel: () -> Unit,
) {
    val state = viewModel.state
    var value by remember { mutableStateOf("") }
    
    if(state.value.data != null) {
        Toast(message = stringResource(id = R.string.comment_sent))
        incrementCommentCount()
        cancel()
    } else if(state.value.error != null) {
        Toast(message = stringResource(id = R.string.something_wrong))
        cancel()
    }

    Dialog(onDismissRequest = cancel) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 30.dp)
                    .background(
                        shape = MaterialTheme.shapes.medium,
                        color = MaterialTheme.colors.background
                    )
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = MaterialTheme.colors.onBackground
                    ),
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = false,
                    placeholder = {
                        Text(
                            text = if (replyingTo.isNullOrBlank()) stringResource(id = R.string.write_comment)
                            else stringResource(id = R.string.replying_to, "@$replyingTo"),
                            color = LightGray,
                            style = MaterialTheme.typography.body1
                        )
                    },
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.md))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CButton(
                        modifier = Modifier.weight(1f),
                        text = R.string.cancel_btn,
                    ) {
                        cancel()
                    }
                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.sm))
                    CButton(
                        modifier = Modifier.weight(1f),
                        text = R.string.comment_btn,
                        yes = true,
                        enabled = value.isNotEmpty()
                    ) {
                        if (replyingTo == null) {
                            viewModel.createComment(
                                postId = postId,
                                body = value
                            )
                        } else {
                            viewModel.replyComment(
                                postId = postId,
                                commentId = commentId!!,
                                body = value
                            )
                        }
                    }
                }
            }
        }
    }
}