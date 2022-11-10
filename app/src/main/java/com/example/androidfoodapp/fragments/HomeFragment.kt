package com.example.androidfoodapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.androidfoodapp.ViewModel.HomeViewModel
import com.example.androidfoodapp.databinding.FragmentHomeBinding
import com.example.androidfoodapp.pojo.Meal

import com.example.androidfoodapp.pojo.MealList
import com.example.androidfoodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observeRandomMeal()

//        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealList?> {
//            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
//                if(response.body() != null){
//                    val randomMeal = response.body()!!.meals[0]
//                    Log.d("HomeFragment", "meal id${randomMeal.idMeal} ${randomMeal.strMeal}")
//
//                    Glide.with(this@HomeFragment)
//                        .load(randomMeal.strMealThumb)
//                        .into(binding.imageRandomMeal)
//                }else{
//                    return
//                }
//            }
//
//            override fun onFailure(call: Call<MealList?>, t: Throwable) {
//
//
//
//            }
//        })




    }

    private fun observeRandomMeal() {
      homeMvvm.observeRandomMeamLiveData().observe(viewLifecycleOwner,object:Observer<Meal>{
          override fun onChanged(t: Meal?) {
              Glide.with(this@HomeFragment)
                  .load(t!!.strMealThumb)
                  .into(binding.imageRandomMeal)
          }

      })
    }

}