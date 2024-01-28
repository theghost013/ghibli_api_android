package com.example.ghibliapi.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.ghibliapi.model.MainViewModel
import com.example.ghibliapi.ui.Routes
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
            HomeScreen()
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
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController? = null, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {


    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = "Welcome to GhibliAPI",
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
        )

        GlideImage(
            model = "https://www.studioghibli.fr/wp-content/uploads/2023/11/cropped-logo-ghibli-recadre-300x236.jpg",
            contentDescription = "Ghibli",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.size(16.dp))

        Button(
            onClick = {
                navController?.navigate(Routes.FilmListScreen.route)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Voir la liste des films")
        }
        
    }
}