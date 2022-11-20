package com.example.androidfoodapp.retrofit

import com.example.androidfoodapp.pojo.CategoryList
import com.example.androidfoodapp.pojo.MealsByCategoryList
import com.example.androidfoodapp.pojo.MealList
import com.example.androidfoodapp.pojo.MealsByCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("random.php")
fun getRandomMeal():Call<MealList>


    @GET("lookup.php")
    fun getRandomMealDetails(
        @Query("i")id:String): Call<MealList>

      @GET("filter.php")
        fun getPopularItems(
          @Query("c")categoryName:String): Call<MealsByCategoryList>
    @GET("categories.php")
    fun getCategories():Call<CategoryList>

    @GET("filter.php")
    fun getMealsByCategory(
        @Query("c")categoryName:String): Call<MealsByCategoryList>
}