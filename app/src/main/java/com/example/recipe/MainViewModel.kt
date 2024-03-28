package com.example.recipe

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _categoriesState = mutableStateOf(RecipeState())
    val categoriesState : State<RecipeState> = _categoriesState //expose value only to external classes

    init {
        //launching a coroutine at the init
        fetchCategories()
    }

    private fun fetchCategories(){
        //this will allow suspend function to be implemented
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                // we use copy to make a list same as the original list but we override some of the values
                _categoriesState.value=_categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )

            }catch (e: Exception){
                _categoriesState.value = _categoriesState.value.copy(
                    loading = false,
                    error = "Error fetching Categories : ${e.message}"
                )
            }
        }
    }

    data class RecipeState(
        val loading : Boolean = true,
        val list : List<Category> = emptyList(),
        val error: String? = null
    )
}
