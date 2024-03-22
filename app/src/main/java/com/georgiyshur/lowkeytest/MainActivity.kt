package com.georgiyshur.lowkeytest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.georgiyshur.lowkeytest.detail.ui.PhotoDetailScreen
import com.georgiyshur.lowkeytest.list.ui.PhotosListScreen
import com.georgiyshur.lowkeytest.ui.theme.LowkeyTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LowkeyTestTheme {
                Navigation()
            }
        }
    }
}

@Composable
private fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.List.route,
    ) {
        composable(
            route = Destinations.List.route,
        ) {
            PhotosListScreen(
                onPhotoClick = {
                    navController.navigate(Destinations.Detail.createRoute(it))
                }
            )
        }
        composable(
            route = Destinations.Detail.route,
            arguments = listOf(
                navArgument(Destinations.ARG_PHOTO_ID) {
                    type = NavType.StringType
                },
            ),
        ) {
            PhotoDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

internal sealed interface Destinations {

    val route: String

    data object List : Destinations {

        override val route = "list"
    }

    data object Detail : Destinations {

        override val route = "detail?$ARG_PHOTO_ID={$ARG_PHOTO_ID}"

        fun createRoute(
            photoId: String
        ): String {
            return "detail?$ARG_PHOTO_ID=$photoId"
        }
    }

    companion object {
        const val ARG_PHOTO_ID = "photoId"
    }
}
