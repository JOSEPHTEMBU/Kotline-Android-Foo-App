package com.example.androidfoodapp.fragments

import android.content.Intent
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
import com.example.androidfoodapp.activities.MealActivity
import com.example.androidfoodapp.databinding.FragmentHomeBinding
import com.example.androidfoodapp.pojo.Meal


class HomeFragment : Fragment() {

    private lateinit var randomMeal: Meal
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm:HomeViewModel


    companion object{
        const val MEAL_ID ="com.example.androidfoodapp.fragments.idMeal"
        const val MEAL_NAME ="com.example.androidfoodapp.fragments.idName"
        const val MEAL_THUMB ="com.example.androidfoodapp.fragments.thumbMeal"
    }

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
        onRandomMealClick()

            //THIS CODE BELOW MOVED TO HOME VIEW MODEL//

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

    private fun onRandomMealClick() {
        binding.imageRandomMeal.setOnClickListener(){
            val intent= Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }

    }

    private fun observeRandomMeal() {
      homeMvvm.observeRandomMeamLiveData().observe(viewLifecycleOwner,
          { meal ->
              Glide.with(this@HomeFragment)
                  .load(meal.strMealThumb)
                  .into(binding.imageRandomMeal)

                   this.randomMeal = meal



      })
    }

}