package com.example.androidfoodapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfoodapp.pojo.Meal
import com.example.androidfoodapp.pojo.MealList
import com.example.androidfoodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(): ViewModel() {
    private var mealDetailliveData= MutableLiveData<Meal>()

    fun getMealDetails(id: String){
        RetrofitInstance.api.getRandomMealDetails(id).enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                mealDetailliveData.value = response.body()!!.meals[0]
            }



            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())


            }
        })


    }

    fun observeMealDetailsLiveData(): LiveData<Meal> {
        return mealDetailliveData
    }


}
