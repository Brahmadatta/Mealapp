package com.example.mealzapp.meals

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealzapp.model.MealsRepository
import com.example.mealzapp.model.response.MealResponse
import com.example.mealzapp.model.response.MealsCategoriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) : ViewModel() {

    private val mealsJob = Job()

    init {
            val scope = CoroutineScope(mealsJob + Dispatchers.IO)
            scope.launch() {
                val meals = getMeals()
                mealsState.value = meals
            }
    }

    val mealsState : MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    private suspend fun getMeals() : List<MealResponse>{
        return repository.getMeals().categories
    }

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
}