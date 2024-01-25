package com.example.ghibliapi.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.ghibliapi.model.MainViewModel
import com.example.ghibliapi.ui.components.FilmRowItem

@Preview(
    showBackground = true,
    showSystemUi = true,
)

@Composable
fun FilmListScreenPreview() {
    FilmListScreen()
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun FilmListScreen(navController: NavController? = null,
    viewModel: MainViewModel = MainViewModel()
) {
    // Récupère la liste de films Ghibli
    val myList = viewModel.myList

    // Récupère l'état de chargement
    val runInProgress = viewModel.runInProgress.collectAsState()

    // Récupère le message d'erreur
    val errorMessage = viewModel.errorMessage.collectAsState()

    // Charge les données Ghibli
    LaunchedEffect(Unit) {
        viewModel.loadGhibliData()
        println("ghibli data loaded: ${myList.size}")
    }

    // Affiche un message d'erreur si nécessaire
    if (errorMessage.value.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = {
                viewModel.errorMessage.value = ""
            },
            title = {
                Text(text = "Erreur")
            },
            text = {
                Text(text = errorMessage.value)
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.errorMessage.value = ""
                    }
                ) {
                    Text(text = "OK")
                }
            }
        )
    }

    // Affiche un indicateur de chargement si nécessaire
    if (runInProgress.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    // Affiche la liste des films Ghibli
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(viewModel.myList.size) {
            FilmRowItem(data = viewModel.myList[it], onClick = {
                println("TODO")
            })
        }
    }
}

