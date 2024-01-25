package com.example.mealzapp.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.mealzapp.R
import com.example.mealzapp.model.response.MealResponse
import com.example.mealzapp.ui.theme.MealzAppTheme

@Composable
fun MealsCategoriesScreen(navigationCallback: (String) -> Unit) {
    val viewModel : MealsCategoriesViewModel = viewModel()
    val meals = viewModel.mealsState.value
    LazyColumn (contentPadding = PaddingValues(16.dp)){
        items(meals){ meal ->
            mealCategory(meal = meal,navigationCallback)
        }
    }
}

@Composable
fun mealCategory(meal : MealResponse,navigationCallback: (String) -> Unit){

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Card (shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .clickable {
                navigationCallback(meal.id)
            }){
        Row(modifier = Modifier.animateContentSize()) {


            //Image
            val painter = rememberAsyncImagePainter(model = meal.imageUrl)

            Image(
                painter = painter,
                contentDescription = stringResource(R.string.app_name),
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth(0.8f)
                .padding(16.dp)
            ) {
                Text(text = meal.name,
                    style = MaterialTheme.typography.titleMedium)

                CompositionLocalProvider {
                    LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                    Text(text = meal.description,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = if (isExpanded) 10 else 4,
                        textAlign = TextAlign.Start
                        )
                }

            }

            Icon(imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown, contentDescription = "Keyboard Up",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded)
                        Alignment.Bottom
                        else
                        Alignment.CenterVertically
                    )
                    .clickable { isExpanded = !isExpanded })

        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealzAppTheme {
        //MealsCategoriesScreen("")
    }
}