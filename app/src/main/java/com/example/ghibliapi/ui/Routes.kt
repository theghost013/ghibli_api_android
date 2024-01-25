package com.example.ghibliapi.ui

sealed class Routes(val route: String) {

    object Screen : Routes("screen")

    object FilmListScreen : Routes("filmListScreen")


}
