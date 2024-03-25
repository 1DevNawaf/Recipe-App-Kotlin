package com.example.recipe

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("categories.php") //here we put what part of our baseURL we will get
    suspend fun getCategories():CategoriesResponse
}