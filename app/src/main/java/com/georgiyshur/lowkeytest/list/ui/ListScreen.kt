@file:OptIn(ExperimentalMaterial3Api::class)

package com.georgiyshur.lowkeytest.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.georgiyshur.lowkeytest.R
import com.georgiyshur.lowkeytest.list.presentation.PhotoItem
import com.georgiyshur.lowkeytest.list.presentation.PhotosListViewModel
import com.georgiyshur.lowkeytest.ui.theme.Typography

@Composable
internal fun ListScreen(
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = contentPadding,
        ) {
            items(
                count = pagingItems.itemCount,
                itemContent = { index ->
                    val item = pagingItems[index] ?: return@items
                    PhotoItem(
                        item = item,
                        onClick = { /* TODO */ },
                    )
                }
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