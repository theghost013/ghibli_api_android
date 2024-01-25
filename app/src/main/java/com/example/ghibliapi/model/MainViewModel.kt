package com.example.ghibliapi.model


// MainViewModel.kt

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ghibliapi.ui.screen.GhibliAPI
import com.example.ghibliapi.ui.screen.GhibliFilm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // StateFlow pour gérer l'état de chargement
    var runInProgress = MutableStateFlow(false)

    // StateFlow pour gérer les messages d'erreur
    var errorMessage = MutableStateFlow("")

    // Liste de films Ghibli
    val myList = mutableListOf<GhibliFilm>()

    // Texte de recherche
    var searchText = mutableStateOf("")

    fun uploadSearchText(newText: String) {
        searchText.value = newText
    }

    fun getFilterListBySearchText() = myList.filter { it.title.contains(searchText.value, ignoreCase = true)}

    // Fonction pour charger les données Ghibli
    fun loadGhibliData() {
        myList.clear()
        errorMessage.value =""
        runInProgress.value = true

        // Utilisez viewModelScope pour gérer automatiquement la coroutine liée à la durée de vie du ViewModel
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val listFilms: List<GhibliFilm> = GhibliAPI.loadFilms()
                if(listFilms.isEmpty()){
                    throw Exception("Pas de résultat")
                }
                myList.clear()

                myList.addAll(listFilms)
                
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = "Erreur lors du chargement des films Ghibli"
            }
            
            runInProgress.value = false
        }
    }
}

