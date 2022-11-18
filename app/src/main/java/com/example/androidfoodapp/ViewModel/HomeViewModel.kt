package com.example.androidfoodapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfoodapp.pojo.*
//import com.bumptech.glide.Glide
import com.example.androidfoodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel():ViewModel() {
   private var randomMealliveData = MutableLiveData<Meal>()
   private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
   fun getRandomMeal() {
      RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList?> {
         override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
            if (response.body() != null) {
               val randomMeal = response.body()!!.meals[0]
               randomMealliveData.value = randomMeal


//            Log.d("HomeFragment", "meal id${randomMeal.idMeal} ${randomMeal.strMeal}")

//            Glide.with(this@HomeFragment1)
//               .load(randomMeal.strMealThumb)
//               .into(binding.imageRandomMeal)
            } else {
               return
            }
         }

         override fun onFailure(call: Call<MealList?>, t: Throwable) {
            Log.d("HomeFragment", t.message.toString())
         }


      })
   }

   fun getPopularItems() {
      RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList?> {
         override fun onResponse(call: Call<MealsByCategoryList?>, response: Response<MealsByCategoryList?>) {
            if (response.body() != null) {
               popularItemsLiveData.value = response.body()!!.meals

            }
         }

         override fun onFailure(call: Call<MealsByCategoryList?>, t: Throwable) {
            Log.d("HomeFragment", t.message.toString())
         }

      })

   }
   fun getCategories(){
      RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList?> {
         override fun onResponse(call: Call<CategoryList?>, response: Response<CategoryList?>) {

         }

         override fun onFailure(call: Call<CategoryList?>, t: Throwable) {

         }


      })
   }
   fun observeRandomMeamLiveData(): LiveData<Meal> {
      return randomMealliveData
   }

   fun observePopularItemsLiveData(): MutableLiveData<List<MealsByCategory>> {
      return popularItemsLiveData

   }
}