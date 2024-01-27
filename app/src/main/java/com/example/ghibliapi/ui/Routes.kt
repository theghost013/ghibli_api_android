package com.example.ghibliapi.ui

sealed class Routes(val route: String) {

    data object Screen : Routes("screen")

    data object FilmListScreen : Routes("filmListScreen")

    data object DetailFilmScreen : Routes("detailFilmScreen/{data}") {
        fun addParam(id: Int) = "detailFilmScreen/$id"
    }
}
