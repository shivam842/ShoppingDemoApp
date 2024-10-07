package com.sagar.shoppingdemoapp.presentation.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagar.shoppingdemoapp.presentation.view.screens.CartPageComposable
import com.sagar.shoppingdemoapp.presentation.view.screens.PDPPageComposable
import com.sagar.shoppingdemoapp.presentation.view.screens.PLPPageComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(innerPadding: PaddingValues) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Offline Shopping App")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        Modifier.clickable {
                            if (navController.currentBackStackEntry?.destination?.route != Screen.PLPPage.route) {
                                navController.popBackStack()
                            } else {
                                // Handle back press to close the app if on start destination
                            }
                        }
                    )
                },
                actions = {
                    // RowScope here, so these icons will be placed horizontally
                    IconButton(onClick = {
                        navController.navigate(route = Screen.CartPage.route)
                    }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                    }
                }
            )
        },
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController, startDestination = Screen.PLPPage.route
        ) {
            composable(Screen.PLPPage.route) {
                PLPPageComposable(
                    navigateToCart = { navController.navigate(Screen.CartPage.route) },
                    navigateToPDP = { id ->
                        navController.navigate(route = Screen.PDPPage.createRoute(itemId = id.toString()))
                    }
                )
            }
            composable(Screen.PDPPage.route) {
                val itemId = navController.currentBackStackEntry?.arguments?.getString("itemId")
                itemId?.let {
                    PDPPageComposable(Integer.parseInt(itemId ?: "0"), LocalContext.current,
                        navigateToCart = {
                            navController.navigate(route = Screen.CartPage.route)
                        }
                    )
                }
            }
            composable(Screen.CartPage.route) {
                CartPageComposable()
            }
        }
        // Handle back press
        BackHandler {
            if (navController.currentBackStackEntry?.destination?.route != Screen.PLPPage.route) {
                navController.popBackStack()
            } else {
                // Handle back press to close the app if on start destination
            }
        }
    }

}