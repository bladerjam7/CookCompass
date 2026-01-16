package com.johnco.cookcompass.model

data class CardItem(
    val id: Int,
    val image: Int,
    val title: String,
    val description: String,
    val time: String,
    val servingSize: String,
    val difficulty: String,
    val tags: ArrayList<String>,
    val recipe: RecipeItem,
)