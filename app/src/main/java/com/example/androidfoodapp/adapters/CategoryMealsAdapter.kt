package com.example.androidfoodapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidfoodapp.databinding.ActivityCategoryMealsBinding
import com.example.androidfoodapp.databinding.MealItemBinding
import com.example.androidfoodapp.pojo.MealList
import com.example.androidfoodapp.pojo.MealsByCategory

class CategoryMealsAdapter():RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewModel>() {

     private var mealList = ArrayList<MealsByCategory>()

     fun setMealsList(mealList:List<MealsByCategory>){
         this.mealList = mealList as ArrayList<MealsByCategory>
         notifyDataSetChanged()
     }
    inner class CategoryMealsViewModel( val binding: MealItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMealsViewModel {
     return CategoryMealsViewModel(
       MealItemBinding.inflate(
          LayoutInflater.from(parent.context)
      )
  )
    }

    override fun onBindViewHolder(holder: CategoryMealsViewModel, position: Int) {
        Glide.with(holder.itemView).load(mealList[position].strMealThumb).into(holder.binding.imageMeal)
         holder.binding.tvMealName.text = mealList[position].strMeal
    }

    override fun getItemCount(): Int {
     return  mealList.size
    }

}