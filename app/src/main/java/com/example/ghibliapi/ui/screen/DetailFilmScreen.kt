package com.example.ghibliapi.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ghibliapi.model.MainViewModel
import com.example.ghibliapi.ui.components.NavBar

@Composable
fun DetailFilmScreen(id: Int, navController: NavHostController? = null, viewModel: MainViewModel = viewModel()) {

    val data = viewModel.getFilterListBySearchText().getOrNull(id)

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        NavBar(navController, searchText = viewModel.searchText)

        Text(
            text = data?.title ?: "Pas de donnée",
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(30.dp))

        if (data != null) {
            Text(
                text = "director: " + data.director,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "producer: " + data.producer,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "release_date: " + data.release_date,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

        }
    }
}