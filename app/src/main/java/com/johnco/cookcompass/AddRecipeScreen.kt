package com.johnco.cookcompass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AddRecipeScreen() {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Add Recipe") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding : PaddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AddRecipeContent(innerPadding)
        }
    }
}

@Composable
fun AddRecipeContent(paddingValues: PaddingValues) {
    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
    BasicInformation()
    Details()
    Tags()
}

@Composable
fun BasicInformation() {
    val recipeTitle = rememberSaveable { mutableStateOf("") }
    val description = rememberSaveable { mutableStateOf("") }
    val imageUrl    = rememberSaveable { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.White),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Text(
            text = "Basic Information",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        )

        Text(
            text = "Recipe Title *",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
        )
        Card(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 0.dp, vertical = 0.dp)
            ) {
                if (recipeTitle.value.isEmpty()) {
                    Text(
                        text = "Enter Recipe Title",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(8.dp),
                        color = colorResource(R.color.outline_input_containers)
                    )
                }

                BasicTextField(
                    modifier = Modifier.padding(8.dp),
                    value = recipeTitle.value,
                    onValueChange = { recipeTitle.value = it },
                    textStyle = MaterialTheme.typography.bodySmall
                )
            }
        }

        Text(
            text = "Description",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
        )
        Card(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 0.dp, vertical = 12.dp),
                contentAlignment = Alignment.TopStart
            ) {
                if (description.value.isEmpty()) {
                    Text(
                        text = "Describe your recipe",
                        style = MaterialTheme.typography.bodySmall,
                        color = colorResource(R.color.outline_input_containers),
                    )
                }

                BasicTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    textStyle = MaterialTheme.typography.bodySmall,
                )
            }
        }

        Text(
            text = "Image URL",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
        )
        Card(
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(horizontal = 0.dp, vertical = 0.dp)
            ) {
                if (imageUrl.value.isEmpty()) {
                    Text(
                        text = "Enter Image URL",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(8.dp),
                        color = colorResource(R.color.outline_input_containers)
                    )
                }

                BasicTextField(
                    modifier = Modifier.padding(8.dp),
                    value = imageUrl.value,
                    onValueChange = { imageUrl.value = it },
                    textStyle = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Composable
fun Details() {
    
}

@Composable
fun Tags() {
    
}