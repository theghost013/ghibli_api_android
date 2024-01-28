package com.example.ghibliapi.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.ghibliapi.model.MainViewModel
import com.example.ghibliapi.ui.Routes
import com.example.ghibliapi.ui.components.FilmRowItem
import com.example.ghibliapi.ui.components.NavBar

@Preview(
    showBackground = true,
    showSystemUi = true,
)

@Composable
fun FilmListScreenPreview() {
    FilmListScreen()
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun FilmListScreen(navController: NavController? = null,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val searchListFilter = viewModel.myList.filter {
        it.title.contains(viewModel.searchText.value)
    }

    // Récupère la liste de films Ghibli
    val myList = viewModel.myList

    // Récupère le message d'erreur
    val errorMessage = viewModel.errorMessage.value

    // Charge les données Ghibli
    LaunchedEffect(Unit) {
        viewModel.loadGhibliData()
        println("ghibli data loaded: ${myList.size}")
    }

    // Affiche un message d'erreur si nécessaire
    if (errorMessage.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = {
                viewModel.errorMessage.value = ""
            },
            title = {
                Text(text = "Erreur")
            },
            text = {
                Text(text = errorMessage)
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

    Column {

        NavBar(navController, searchText = viewModel.searchText, searchIcon = true)

        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {

            Spacer(modifier = Modifier.size(8.dp))

            // Affiche la liste des films Ghibli
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchListFilter.size) {
                    FilmRowItem(data = searchListFilter[it], onClick = {
                        println("click on item: ${myList[it]}")
                        navController?.navigate(Routes.DetailFilmScreen.addParam(it))
                    })
                }
            }
        }
    }
}

