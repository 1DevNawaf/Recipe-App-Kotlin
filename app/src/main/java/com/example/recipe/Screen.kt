package com.example.recipe

sealed class Screen(val route:String) {
    object RecipeScreen:Screen("RecipeScreen")
    object DetailScreen:Screen("DetailScreen")
}