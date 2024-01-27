package com.example.ghibliapi.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ghibliapi.model.MainViewModel
import com.example.ghibliapi.ui.components.ErrorView
import com.example.ghibliapi.ui.components.FilmRowItem
import com.example.ghibliapi.ui.components.SearchBar
import com.example.ghibliapi.ui.theme.GhibliApiTheme

@Preview(
    showBackground = true,
    showSystemUi = true,
)

@Composable
fun ScreenPreview() {
    GhibliApiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Screen()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)

@Composable
fun ScreenPreviewDark() {
    GhibliApiTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Screen()
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Screen(navController: NavHostController? = null, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val searchListFilter = viewModel.myList.filter {
        it.title.contains(viewModel.searchText.value)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        SearchBar(searchText = viewModel.searchText)

        if(viewModel.runInProgress.value) {
            CircularProgressIndicator()
        }


        if(viewModel.errorMessage.value.isNotBlank()) {
            ErrorView(message = viewModel.errorMessage.value)
        }

        Spacer(Modifier.size(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(searchListFilter.size) {
                FilmRowItem(data = searchListFilter[it], onClick = {
                    //navController?.navigate(Routes.DetailScreen.addParam(it))
                    println("nav")
                } )
            }
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = { viewModel.uploadSearchText("")},
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear filter")
            }

            Spacer(Modifier.size(8.dp))

            Button(
                onClick = {
                          viewModel.loadGhibliData()
                          },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Load data")
            }
        }
    }
}