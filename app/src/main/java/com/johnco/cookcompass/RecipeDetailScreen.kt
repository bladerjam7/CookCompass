package com.johnco.cookcompass

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.johnco.cookcompass.ui.theme.CheckBoxContainer
import com.johnco.cookcompass.ui.theme.DirectionCheckBoxColor
import com.johnco.cookcompass.ui.theme.RecipeDescriptionStyle
import com.johnco.cookcompass.ui.theme.RecipeDirectionsTitleStyle
import com.johnco.cookcompass.ui.theme.RecipeIngredientsDescriptionStyle
import com.johnco.cookcompass.ui.theme.RecipeIngredientsTitleStyle
import com.johnco.cookcompass.ui.theme.RecipeTitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipeId: Int?,
    homeViewModel: HomeViewModel = viewModel()
) {
    val navController = LocalNavController.current
    val context = LocalContext.current

    val card = homeViewModel.cards.collectAsState()
    val recipeItem =
        recipeId?.let {
            card.value[recipeId]
        } ?: run {
            navController?.navigateUp()
            Toast.makeText(context, "Recipe not found", Toast.LENGTH_SHORT).show()
            return
        }

    val ingredientCheckedState = remember {
        mutableStateListOf<Boolean>().apply {
            addAll(List(recipeItem.recipe.ingredients.size) { false })
        }
    }

    val directionCheckedState = remember {
    mutableStateListOf<Boolean>().apply {
        addAll(List(recipeItem.recipe.directions.size) { false })
    }
}

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Recipe",
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding: PaddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {

                Image(
                    painter = painterResource(id = recipeItem.image), // Replace with your image
                    contentDescription = recipeItem.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                Column(modifier = Modifier.padding(16.dp)){
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp))
                    {
                        Text(
                            text = recipeItem.title,
                            style = RecipeTitleStyle
                        )

                        Text(
                            text = recipeItem.description,
                            style = RecipeDescriptionStyle
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            IconWithText(painterResource(R.drawable.time), recipeItem.time)
                            IconWithText(
                                painterResource(R.drawable.servings),
                                recipeItem.servingSize
                            )

                            Surface(
                                modifier = Modifier.wrapContentHeight(),
                                shape = RoundedCornerShape(8.dp),
                                color = Color.Black,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(horizontal = 9.dp, vertical = 4.dp)
                                        .background(Color.Black),
                                    text = recipeItem.difficulty,
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            for (i in 0..recipeItem.tags.size - 1) {
                                if (i > 2) {
                                    TagChip("+${recipeItem.tags.size - 3}")
                                    break
                                } else {
                                    TagChip(recipeItem.tags[i])
                                }
                            }
                        }

                        HorizontalDivider(modifier = Modifier.height(12.dp))
                    }

                    Card(
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Ingredients",
                                style = RecipeIngredientsTitleStyle,
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            recipeItem.recipe.ingredients.forEachIndexed { index, ingredient ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(24.dp)
                                        .offset(x = (-16).dp)
                                        .clickable {
                                            ingredientCheckedState[index] = !ingredientCheckedState[index]
                                        }
                                ) {
                                    Checkbox(
                                        checked = ingredientCheckedState[index],
                                        onCheckedChange = { isChecked ->
                                            ingredientCheckedState[index] = isChecked
                                        },
                                        colors = CheckboxDefaults.colors(uncheckedColor = CheckBoxContainer)
                                    )
                                    Text(
                                        modifier = Modifier.offset(x = (-4).dp),
                                        text = ingredient,
                                        style = RecipeIngredientsDescriptionStyle
                                    )
                                }
                            }
                        }
                    }

                }
            }

            item {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = "Directions",
                    style = RecipeDirectionsTitleStyle,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(recipeItem.recipe.directions.size) { index ->
                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clickable{
                            directionCheckedState[index] = !directionCheckedState[index]
                        },
                    verticalAlignment = Alignment.Top,
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if(directionCheckedState[index]) DirectionCheckBoxColor else Color.Transparent,
                        border = BorderStroke(1.dp, Color.Gray),
                        modifier = Modifier.height(30.dp).width(30.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            if(!directionCheckedState[index]) {
                                Text(
                                    text = "${index + 1}",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            } else {
                                Image(
                                    painter = painterResource(R.drawable.baseline_check_24),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.White),
                                    contentScale = ContentScale.Crop,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                    Text(
                        modifier = Modifier.weight(1f),
                        text = recipeItem.recipe.directions[index],
                        style = RecipeIngredientsDescriptionStyle,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RecipeDetailScreenPreview() {
    CompositionLocalProvider(LocalNavController provides null) {
        RecipeDetailScreen(recipeId = 0)
    }
}