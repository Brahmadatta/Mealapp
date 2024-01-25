package com.example.mealzapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.details.MealDetailsScreen
import com.example.mealzapp.details.MealDetailsViewModel
import com.example.mealzapp.meals.MealsCategoriesScreen
import com.example.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                    FoodiezApp()
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    @Composable
    private fun FoodiezApp(){
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "destination_meals_list"){
            composable(route = "destination_meals_list"){
                MealsCategoriesScreen(){navigationMealId ->
                    navController.navigate("destination_meal_details/${navigationMealId}")
                }
            }
            composable(route = "destination_meal_details/{meal_category_id}",
                arguments = listOf(navArgument("meal_category_id"){
                    type = NavType.StringType
                })){
                val viewModel : MealDetailsViewModel = viewModel()
                   MealDetailsScreen(viewModel.mealState.value)
                }
        }
    }
}
