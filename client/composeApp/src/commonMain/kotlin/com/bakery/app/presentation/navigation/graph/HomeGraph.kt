package com.bakery.app.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.bakery.core.presentation.navigation.Destination

fun NavGraphBuilder.homeGraph() {
    navigation<Destination.HomeGraph>(
        startDestination = Destination.Home
    ) {
        composable<Destination.Home> {
        }

        composable<Destination.Profile> {
        }

        composable<Destination.Settings> {
        }

        composable<Destination.Products> {
        }

        composable<Destination.ProductDetails> {
        }

        composable<Destination.Notifications> {
        }

        composable<Destination.Cart> {
        }

        composable<Destination.Checkout> {
        }

        composable<Destination.Orders> {
        }

        composable<Destination.OrderDetails> {
        }
    }
}
