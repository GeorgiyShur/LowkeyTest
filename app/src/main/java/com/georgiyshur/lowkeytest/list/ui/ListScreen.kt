@file:OptIn(ExperimentalMaterial3Api::class)

package com.georgiyshur.lowkeytest.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = onClick,
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Surface(
            modifier = Modifier.size(80.dp),
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 8.dp,
        ) {
            AsyncImage(
                model = item.url,
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = item.name,
                style = Typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.photographer,
                style = Typography.bodyLarge,
            )
        }
    }
}