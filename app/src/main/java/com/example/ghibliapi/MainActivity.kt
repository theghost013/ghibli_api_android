package com.example.ghibliapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ghibliapi.model.MainViewModel
import com.example.ghibliapi.ui.Routes
import com.example.ghibliapi.ui.screen.DetailFilmScreen
import com.example.ghibliapi.ui.screen.FilmListScreen
import com.example.ghibliapi.ui.screen.Screen
import com.example.ghibliapi.ui.theme.GhibliApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GhibliApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController : NavHostController = rememberNavController()
    val viewModel : MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = Routes.FilmListScreen.route) {

        composable(Routes.Screen.route) {
            Screen(navController, viewModel)
        }

        composable(Routes.FilmListScreen.route) {
            FilmListScreen(navController, viewModel = viewModel)
        }

        composable(
            route = Routes.DetailFilmScreen.route,
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("data", 0) ?: 0
            DetailFilmScreen(id, navController = navController, viewModel)
        }

    }
}