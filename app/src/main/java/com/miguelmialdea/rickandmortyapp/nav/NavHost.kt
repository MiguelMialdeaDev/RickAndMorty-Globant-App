package com.miguelmialdea.rickandmortyapp.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.miguelmialdea.rickandmortyapp.detail.DetailScreen
import com.miguelmialdea.rickandmortyapp.home.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home,
        modifier = modifier
    ) {
        composable<Routes.Home> {
            HomeScreen(
                onCharacterClick = { id ->
                    navController.navigate(Routes.Detail(id))
                }
            )
        }

        composable<Routes.Detail> { backStackEntry ->
            val detail: Routes.Detail = backStackEntry.toRoute()
            DetailScreen(
                characterId = detail.id,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}