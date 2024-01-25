package com.example.mealzapp.model

import com.example.mealzapp.model.api.MealsApiWebService
import com.example.mealzapp.model.response.MealResponse
import com.example.mealzapp.model.response.MealsCategoriesResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MealsRepository(private val webService: MealsApiWebService = MealsApiWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id : String) : MealResponse? {
        return cachedMeals.firstOrNull(){
            it.id == id
        }
    }

    companion object {
        @Volatile
        private var instance : MealsRepository? = null

        fun getInstance() = instance?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it}
        }

    }
}