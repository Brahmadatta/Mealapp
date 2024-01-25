package com.example.mealzapp.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.mealzapp.model.response.MealResponse
import kotlin.math.max
import kotlin.math.min

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    var scrollState = rememberLazyListState()

    //val offset = min(1f,1 - (scrollState.value / 600f))
    val offset = min(1f,1-(scrollState.firstVisibleItemScrollOffset / 600f
            + scrollState.firstVisibleItemIndex))
    val size by animateDpAsState(targetValue = max(100.dp, 200.dp * offset), label = "")
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        )
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = meal?.imageUrl),
                            contentDescription = null,
                            modifier = Modifier
                                .size(size)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                        )
                    }
                    Text(
                        text = meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState){
                items(dummyContentList){ dummyItem ->
                    Text(text = dummyItem, modifier = Modifier.padding(24.dp))
                }
            }
        }


    }

//    var profilePictureState by remember { mutableStateOf(MealProfilePictureState.Normal) }
//    val transition = updateTransition(targetState = profilePictureState, label = "")
//    val imageSizeDp by transition.animateDp(
//        targetValueByState =  {
//            it.size
//        },
//        label = ""
//    )
//    val color by transition.animateColor (
//        targetValueByState = {
//            it.color
//        }, label = ""
//    )
//    val widthSize by transition.animateDp (
//        targetValueByState = {
//            it.borderWidth
//        },
//        label = ""
//    )
//    Column {
//        Row {
//            Card(modifier = Modifier.padding(16.dp),
//                shape = CircleShape,
//                border = BorderStroke(
//                    width = widthSize,
//                    color = color
//                )
//            ) {
//                Image(
//                    painter = rememberAsyncImagePainter(model = meal?.imageUrl),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(imageSizeDp)
//                        .clip(CircleShape)
//                        .padding(8.dp)
//                        .border(2.dp, Color.White, CircleShape)
//                )
//            }
//            Text(
//                text = meal?.name ?: "default name",
//                modifier = Modifier
//                    .padding(16.dp)
//                    .align(Alignment.CenterVertically)
//            )
//        }
//        Button(onClick = {
//            profilePictureState = if (profilePictureState == MealProfilePictureState.Normal)
//                MealProfilePictureState.Expanded
//            else
//                MealProfilePictureState.Normal
//        }) {
//            Text(text = "Change state of meal profile picture")
//        }
//    }
}

enum class MealProfilePictureState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp)
}