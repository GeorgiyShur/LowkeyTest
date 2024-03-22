@file:OptIn(ExperimentalMaterial3Api::class)

package com.georgiyshur.lowkeytest.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.georgiyshur.lowkeytest.R
import com.georgiyshur.lowkeytest.list.presentation.PhotoItem
import com.georgiyshur.lowkeytest.list.presentation.PhotosListViewModel
import com.georgiyshur.lowkeytest.ui.theme.Typography

@Composable
internal fun PhotosListScreen(
    onPhotoClick: (String) -> Unit,
    viewModel: PhotosListViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.list_title))
                }
            )
        },
    ) { contentPadding ->
        val pagingItems = viewModel.photosPagingData.collectAsLazyPagingItems()
        val pagingItemsRefreshState by remember(pagingItems) {
            derivedStateOf { pagingItems.loadState.refresh }
        }

        val pullRefreshState = rememberPullToRefreshState()
        if (pullRefreshState.isRefreshing) {
            LaunchedEffect(true) {
                pagingItems.refresh()
            }
        }
        LaunchedEffect(pagingItemsRefreshState) {
            if (pagingItemsRefreshState != LoadState.Loading) {
                pullRefreshState.endRefresh()
            }
        }

        Box(
            modifier = Modifier
                .nestedScroll(pullRefreshState.nestedScrollConnection)
                .padding(contentPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                items(
                    count = pagingItems.itemCount,
                    itemContent = { index ->
                        val item = pagingItems[index] ?: return@items
                        PhotoItem(
                            item = item,
                            onClick = { onPhotoClick(item.id) },
                        )
                    }
                )
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullRefreshState,
            )
        }
    }
}

@Composable
private fun PhotoItem(
    item: PhotoItem,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            shadowElevation = 16.dp,
            onClick = onClick,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = item.avgColor.copy(alpha = 0.3f))
            ) {
                AsyncImage(
                    model = item.url,
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.7f),
                                )
                            )
                        )
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(12.dp),
                    text = stringResource(R.string.by_photographer, item.photographer),
                    style = Typography.bodyMedium,
                    color = Color.White,
                )
            }
        }
    }
}