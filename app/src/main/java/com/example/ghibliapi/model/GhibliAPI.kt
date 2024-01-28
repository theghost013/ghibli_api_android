package com.example.ghibliapi.ui.screen

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Objects

fun main() {
    val res = GhibliAPI.LoadOneFilm("2baf70d1-42bb-4437-b551-e5fed5a87abe")
    println(res)
}

object GhibliAPI {
    private val client = OkHttpClient()
    private val gson = Gson()

    private const val URL_API = "https://ghibliapi.vercel.app/films"

    fun loadFilms(): List<GhibliFilm> {
        val json = sendGet(URL_API)
        return gson.fromJson(json, Array<GhibliFilm>::class.java).toList()
    }

    fun LoadOneFilm(id: String): GhibliFilm  {
        val json = sendGet(URL_API + "/" + id)
        return gson.fromJson(json, GhibliFilm::class.java)
    }

    // Méthode générique qui prend en entrée une URL, exécute la requête et retourne la réponse JSON
    private fun sendGet(url: String): String {
        println("URL : $url")
        val request = Request.Builder().url(url).get().build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrecte : ${it.code}")
            }
            it.body?.string() ?: throw Exception("Réponse du serveur vide.")
        }
    }
}

/* -------------------------------- */
// Bean
/* -------------------------------- */
data class GhibliFilm(
    var id: String,
    var title: String,
    var director: String,
    var producer: String,
    var release_date: String,
    // ... Ajoutez d'autres champs selon les besoins
)
