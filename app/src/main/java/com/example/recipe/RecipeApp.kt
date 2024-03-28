package com.example.recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController) {
    val recipeViewModel : MainViewModel= viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable(Screen.RecipeScreen.route){
            RecipeScreen(viewState=viewState, navigateToDetail ={
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
                /*
                currentBackStackEntry: all of the flow of screens are stored in the back stack entry
                savedStateHandle: save data that are need to be passed and used in multiple screen
                set("cat",it): set a key value pair inside the save state handle, so the key is used to retrieve the data in another screen
                */
            } 
            )
        }
        composable(Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
                get<Category>("cat") ?: Category("","","","")
            CategoryDetailScreen(category = category)
            /*
                get<Category>("cat"): will get the category that have the key "cat"
                if there is no value of that category(null) it will pass to it a category that heave empty string values
            */
            
        }
    }
}