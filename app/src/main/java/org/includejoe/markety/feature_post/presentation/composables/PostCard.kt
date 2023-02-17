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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.NewReleases
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import org.includejoe.markety.base.presentation.composables.Avatar
import org.includejoe.markety.base.presentation.theme.ui.spacing
import org.includejoe.markety.feature_post.data.remote.dto.PostDTO
import org.includejoe.markety.feature_post.presentation.HomeViewModel
import org.includejoe.markety.R
import org.includejoe.markety.base.domain.model.UserInfo
import org.includejoe.markety.base.presentation.composables.Actions
import org.includejoe.markety.base.presentation.composables.DateFormatter
import org.includejoe.markety.base.presentation.composables.Name
import org.includejoe.markety.base.presentation.theme.ui.Green
import org.includejoe.markety.base.util.Screens
import java.util.*

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: PostDTO,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp,)
    ) {
        UserInfo(user = post.user) {
            navController.navigate(
                route = if (viewModel.baseApp.userDetails.value?.username == post.user.username)
                            Screens.ProfileScreen.route
                        else Screens.ProfileScreen.route + "/${post.user.username}"
            )
        }
        PostDetails(post) {
            if (onClick != null) {
                onClick()
            }
        }
        ImageSlider(
            images = listOf(
                post.image1,
                post.image2,
                post.image3,
            )
        ) {
            if (onClick != null) {
                onClick()
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.sm))
        Actions(
            post = post,
            iconSize = 24.dp,
        )
    }
}

@Composable
private fun UserInfo(
    user: UserInfo,
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = MaterialTheme.spacing.sm)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Avatar(
                isDarkTheme = viewModel.baseApp.isDarkTheme.value,
                modifier = Modifier.size(40.dp),
                src = user.profileImage
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.xs))
            Column {
                Name(
                    isVendor = user.isVendor,
                    busName = user.busName,
                    firstName = user.firstName,
                    lastName = user.lastName
                )
                Text(
                    text = "@${user.username}",
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
                fontWeight = FontWeight.Bold
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = if (post.isSold) R.string.sold else R.string.available),
                    style = MaterialTheme.typography.body1,
                    color = if(post.isSold) MaterialTheme.colors.error else Green,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(3.dp))
                Icon(
                    imageVector = if(post.isSold)  Icons.Default.NotInterested
                    else Icons.Default.EventAvailable,
                    modifier = Modifier.size(15.dp),
                    contentDescription = null,
                    tint = if(post.isSold) MaterialTheme.colors.error else Green
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.ghc, post.price),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = if(post.isNew) R.string.is_new else R.string.is_used),
                    style = MaterialTheme.typography.body1,
                    color = if(post.isNew) Green else MaterialTheme.colors.onBackground.copy(alpha = 0.5f),
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.width(3.dp))
                Icon(
                    imageVector = if(post.isNew)  Icons.Default.NewReleases
                    else Icons.Default.CheckCircle,
                    modifier = Modifier.size(15.dp),
                    contentDescription = null,
                    tint = if(post.isNew) Green else MaterialTheme.colors.onBackground.copy(alpha = 0.5f)
                )
            }
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DateFormatter(dateString = post.createdAt)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ImageSlider(
    images: List<String>,
    onClick: () -> Unit
) {
    val state = rememberPagerState()
    val imageUrl = remember { mutableStateOf("") }

    HorizontalPager(
        state = state,
        count = images.size,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onClick()
            }
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
                        .size(5.dp)
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