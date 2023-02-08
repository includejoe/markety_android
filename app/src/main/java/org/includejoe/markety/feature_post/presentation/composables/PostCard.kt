package org.includejoe.markety.feature_post.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.presentation.HomeViewModel
import org.includejoe.markety.R
import org.includejoe.markety.base.presentation.composables.CButton
import org.includejoe.markety.base.presentation.theme.ui.LightGray
import org.w3c.dom.Comment
import java.util.*

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: PostDTO,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(bottom = 5.dp,)
    ) {
        UserInfo()
        PostDetails(post) { onClick() }
        ImageSlider(
            images = listOf(
                post.image1,
                post.image2,
                post.image3,
            )
        )
        Actions(post)
    }
}

@Composable
private fun UserInfo(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = MaterialTheme.spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                isDarkTheme = viewModel.baseApp.isDarkTheme.value,
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
            Column {
                Text(
                    text = "The Hokage's Store",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "@hokage",
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.body1,
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Composable
private fun PostDetails(
    post: PostDTO,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.sm)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            },
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = post.name,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = stringResource(id = if (post.isSold) R.string.sold else R.string.available),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(id = R.string.ghc, post.price),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
                fontWeight = FontWeight.Bold
            )
        }
        if(!post.description.isNullOrEmpty()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = post.description,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ImageSlider(
    images: List<String>,
) {
    val state = rememberPagerState()
    val imageUrl = remember { mutableStateOf("") }

    HorizontalPager(
        state = state,
        count = images.size,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.9f)
    ) {page ->
        imageUrl.value = images[page]
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(contentAlignment = Alignment.BottomCenter) {
                val painter = rememberAsyncImagePainter(imageUrl.value)
                Image(
                    painter = painter,
                    contentDescription = "post image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
    SliderIndicator(totalDots = images.size, selectedIndex = state.currentPage)
}

@Composable
private fun SliderIndicator(
    totalDots: Int,
    selectedIndex: Int,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {
        items(totalDots) {index ->
            if(index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colors.primary)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color = MaterialTheme.colors.primary.copy(alpha = 0.5f))
                )
            }
            
            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 1.dp))
            }
        }
    }
}

@Composable
private fun Actions(
    post: PostDTO
) {
    val iconSize = 24.dp
    var showCommentDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.sm),
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
                    text = post.likes.size.toString(),
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
                    text = post.comments.size.toString(),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1
                )
            }
        }
        Icon(
            imageVector = Icons.Outlined.AddShoppingCart,
            modifier = Modifier.size(iconSize),
            contentDescription = "save icon",
            tint = MaterialTheme.colors.onBackground
        )
    }

    if(showCommentDialog) {
        CommentDialog(cancel = { showCommentDialog = false }) {

        }
    }
}

@Composable
private fun CommentDialog(
    cancel: () -> Unit,
    submit: () -> Unit,
) {
    var value by remember { mutableStateOf("") }

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
                            text = stringResource(id = R.string.write_comment),
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
                        yes = true
                    ) {
                        submit()
                    }
                }
            }
        }
    }
}
