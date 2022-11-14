package com.example.androidfoodapp.activities

import android.content.Intent
import android.hardware.Camera.Area
import android.icu.util.ULocale.Category
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.androidfoodapp.R
import com.example.androidfoodapp.ViewModel.HomeViewModel
import com.example.androidfoodapp.ViewModel.MealViewModel
import com.example.androidfoodapp.databinding.ActivityMainBinding
import com.example.androidfoodapp.databinding.ActivityMealBinding
import com.example.androidfoodapp.fragments.HomeFragment
import com.example.androidfoodapp.pojo.Meal
import retrofit2.http.Url
import java.net.HttpCookie.parse

class MealActivity : AppCompatActivity() {


    private lateinit var mealId:String
    private lateinit var mealName:String
    private lateinit var mealThumb:String
    private lateinit var mealMvvm: MealViewModel
    private lateinit var youTubeLink: String

   private lateinit var binding: ActivityMealBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationInviews()
        loadingCase()

        mealMvvm.getMealDetails()
        observeMealDetailsLiveData()
        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.imageYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youTubeLink))
            startActivity(intent)
        }
    }


    private fun observeMealDetailsLiveData() {
        mealMvvm.observeMealDetailsLiveData().observe(this,object: Observer<Meal> {
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t
                binding.tvCategory.text = "Category: ${meal!!.strCategory}"
                binding.tvArea.text = "Area: ${meal.strArea}"
                binding.tvInstructions.text = "Instructions: ${meal.strInstructions}"
                youTubeLink = meal.strYoutube

            }
        })

    }

    private fun setInformationInviews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imageMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun getMealInformationFromIntent() {
        val intent =intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb= intent.getStringExtra(HomeFragment.MEAL_THUMB)!!

    }
                private fun loadingCase(){
            binding.tvCategory.visibility = View.INVISIBLE
            binding.tvArea.visibility = View.INVISIBLE
            binding.imageMealDetail.visibility =View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
            binding.btnAddTofavorites.visibility=View.INVISIBLE
            binding.imageYoutube.visibility = View.INVISIBLE
            binding.tvInstructions.visibility=View.INVISIBLE


    }
    private fun onResponseCase(){

        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imageMealDetail.visibility =View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddTofavorites.visibility=View.VISIBLE
        binding.imageYoutube.visibility = View.VISIBLE
        binding.tvInstructions.visibility=View.VISIBLE

    }
}