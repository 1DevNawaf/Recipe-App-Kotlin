package com.example.recipe

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.recipe.ui.theme.fontTangerine

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,viewState:MainViewModel.RecipeState,navigateToDetail:(Category)->Unit) {
    //val recipeViewModel : MainViewModel = viewModel()
    Column(
        modifier=Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier=Modifier.fillMaxWidth().background(color = Color(0XFF00668b))
        ) {
            Text(
                text = "Cook Book",
                style = TextStyle(fontFamily = fontTangerine),
                fontSize = 50.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Quick & Easy",
                style = TextStyle(fontFamily = fontTangerine),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                //when the list is loading
                viewState.loading -> CircularProgressIndicator(modifier.align(Alignment.Center))

                //when there is an error
                viewState.error != null -> Text("Error Occurred")

                //here we display the full list
                else -> {
                    CategoryList(categories = viewState.list, navigateToDetail)
                }
            }
        }
    }
}


//the group of all items

@Composable
fun CategoryList(categories:List<Category>,navigateToDetail:(Category)->Unit) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier
        .fillMaxSize()
        .padding(4.dp)) {
        items(items = categories){
                category ->
            CategoryItem(category = category,navigateToDetail)
        }
    }
}


//the look of each item
@Composable
fun CategoryItem(category: Category,navigateToDetail:(Category)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .border(
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(2.dp, Color(0XFF99aab5))
            )
            .clickable { navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = "Description Type : ${category.strCategory}",
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
        )
        Text(
            text =category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(4.dp)
        )
    }
}