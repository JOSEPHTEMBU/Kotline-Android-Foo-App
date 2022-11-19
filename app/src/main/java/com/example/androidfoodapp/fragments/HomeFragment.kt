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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidfoodapp.ViewModel.HomeViewModel
import com.example.androidfoodapp.activities.MealActivity
import com.example.androidfoodapp.adapters.CategoriesAdapter
import com.example.androidfoodapp.adapters.MostPopularAdapter
import com.example.androidfoodapp.databinding.FragmentHomeBinding
import com.example.androidfoodapp.pojo.MealsByCategory
import com.example.androidfoodapp.pojo.Meal


class HomeFragment : Fragment() {
     private lateinit var popularItemsaAdapter: MostPopularAdapter
     private lateinit var randomMeal: Meal
     private lateinit var binding: FragmentHomeBinding
     private lateinit var homeMvvm:HomeViewModel
     private lateinit var categoriesAdapter: CategoriesAdapter

    companion object{
        const val MEAL_ID ="com.example.androidfoodapp.fragments.idMeal"
        const val MEAL_NAME ="com.example.androidfoodapp.fragments.idName"
        const val MEAL_THUMB ="com.example.androidfoodapp.fragments.thumbMeal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

         popularItemsaAdapter =MostPopularAdapter()

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

          preparePopularItemsRecyclerView()

          homeMvvm.getRandomMeal()

          observeRandomMeal()

          onRandomMealClick()

          homeMvvm.getPopularItems()

           observePopularItemsLiveData()
           onPopularItemClick()
           prepareCategoriesRecyclerView()
           homeMvvm.getCategories()
           observeCategoriesLiveData()

//        log.d("TESTS","meal id ${randomMeal.idMeal} name  ${randomMeal.strMeal}")

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

    private fun prepareCategoriesRecyclerView() {
           categoriesAdapter=CategoriesAdapter()
           binding.category .apply {
            layoutManager =    GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter



       }
    }

    private fun observeCategoriesLiveData() {

        homeMvvm.observeCategoriesLiveData().observe(viewLifecycleOwner, Observer { categories->

//            categories.forEach{ category ->
                categoriesAdapter.setCategoryList(categories)
//           Log.d("tests",category.strCategory)


        })



    }

    private fun onPopularItemClick() {
        popularItemsaAdapter.onItemClick = {Meal ->

            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,Meal.idMeal)
            intent.putExtra(MEAL_NAME,Meal.strMeal)
            intent.putExtra(MEAL_THUMB,Meal.strMealThumb)
           startActivity(intent)

        }
    }

    private fun preparePopularItemsRecyclerView() {

        binding.overPopular.apply {
            layoutManager =    LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter=popularItemsaAdapter

        }
    }
    private fun observePopularItemsLiveData() {
        homeMvvm.observePopularItemsLiveData().observe(viewLifecycleOwner,

            { MealList ->
                popularItemsaAdapter.setMeals(mealsList = MealList as ArrayList<MealsByCategory>)
            })
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