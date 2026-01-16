package com.johnco.cookcompass

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.johnco.cookcompass.model.CardItem
import com.johnco.cookcompass.ui.theme.CookCompassTheme
import com.johnco.cookcompass.ui.theme.RecipeDescriptionStyle
import com.johnco.cookcompass.ui.theme.RecipeTagsStyle
import com.johnco.cookcompass.ui.theme.RecipeTitleStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    val cards = homeViewModel.cards.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "My Recipes", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    Button(
                        modifier = Modifier
                            .height(32.dp)
                            .padding(PaddingValues(end = 16.dp)),
                        onClick = {
                            /*TODO*/
                        },
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 5.5.dp)
                    ) {
                        Text(text = "+ Add Recipe", style = MaterialTheme.typography.labelSmall)
                    }
                },
                windowInsets = TopAppBarDefaults.windowInsets
            )
        }) { innerPadding: PaddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainContent(cards.value)
        }
    }
}

@Composable
fun MainContent(cards: List<CardItem>) {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    LazyColumn(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
        items(cards) { card ->
            Recipe(card)
        }
    }
}

@Composable
fun Recipe(recipe: CardItem) {
    val navController = LocalNavController.current

    Card(
        shape = RoundedCornerShape(16.dp),
        //elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                navController?.navigate("recipeDetail/$recipe")
            },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
    ) {
        Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            TaskList()
            Box {
                Image(
                    painter = painterResource(id = recipe.image), // Replace with your image
                    contentDescription = recipe.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )

                IconButton(
                    onClick = { /* TODO: Menu */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                            RoundedCornerShape(8.dp)
                        )
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More options")
                }
            }

            Column(
                modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = recipe.title,
                    style = RecipeDescriptionStyle
                )

                Text(
                    text = recipe.description,
                    fontSize = 14.sp,
                    color = colorResource(R.color.storm_grey)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    IconWithText(painterResource(R.drawable.time), recipe.time)
                    IconWithText(painterResource(R.drawable.servings), recipe.servingSize)

                    Surface(
                        modifier = Modifier.wrapContentHeight(),
                        shape = RoundedCornerShape(8.dp),
                        color = Color.Black,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 9.dp, vertical = 4.dp)
                                .background(Color.Black),
                            text = recipe.difficulty,
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (i in 0..recipe.tags.size - 1) {
                        if (i > 2) {
                            TagChip("+${recipe.tags.size - 3}")
                            break
                        } else {
                            TagChip(recipe.tags[i])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun IconWithText(icon: Painter, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = icon, contentDescription = null, modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, fontSize = 14.sp, color = colorResource(R.color.storm_grey))
    }
}

@Composable
fun TagChip(text: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.background,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
            style = RecipeTagsStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CompositionLocalProvider(LocalNavController provides null) {
        HomeScreen()
    }
}