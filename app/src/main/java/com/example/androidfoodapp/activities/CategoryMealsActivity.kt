package com.example.androidfoodapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidfoodapp.R
import com.example.androidfoodapp.ViewModel.CategoryMealsViewModel
import com.example.androidfoodapp.adapters.CategoryMealsAdapter
import com.example.androidfoodapp.databinding.ActivityCategoryMealsBinding
import com.example.androidfoodapp.databinding.ActivityMealBinding
import com.example.androidfoodapp.fragments.HomeFragment
import com.example.androidfoodapp.pojo.MealList

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryMealsBinding

    lateinit var categoryMealsViewModel: CategoryMealsViewModel

    lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareRecyclerView()
        categoryMealsViewModel = ViewModelProviders.of(this)[categoryMealsViewModel::class.java]

        categoryMealsViewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        categoryMealsViewModel.observeMealsLiveData().observe(this, Observer { MealList ->

            binding.tvCategoryCount.text = MealList.size.toString()

            categoryMealsAdapter.setMealsList(MealList)


//            MealList.forEach{
//          Log.d("tests",it.strMeal)
//
//      }

        })
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = categoryMealsAdapter
        }

    }

}