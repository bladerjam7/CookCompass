package com.johnco.cookcompass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.johnco.cookcompass.model.CardItem
import com.johnco.cookcompass.model.RecipeItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    private val _cards = MutableStateFlow<List<CardItem>>(emptyList())
    val cards: StateFlow<List<CardItem>> = _cards

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _cards.value = arrayListOf(
                CardItem(
                    1,
                    R.drawable.classic_spaghetti_carbonara,
                    "Classic Spaghetti Carbonara",
                    "A traditional Italian pasta dish with eggs, chesse, and pancetta.",
                    "25m",
                    "4",
                    "Medium",
                    arrayListOf("Pasta", "Italian", "Quick"),
                    RecipeItem(
                        listOf(
                            "200g (7 oz) spaghetti",
                            "100g (3.5 oz) guanciale",
                            "2 large eggs",
                            "1 large egg yolk",
                            "60g (about 1 cup packed) Pecorino Romano",
                            "Freshly cracked black pepper",
                            "Salt to taste"
                        ),
                        listOf(
                            "Whisk together the eggs, yolk, Pecorino Romano, and black pepper until thick.",
                            "Cut the guanciale into strips or cubes.",
                            "Place the guanciale in a cold pan and cook over medium heat until crispy and the fat has rendered.",
                            "Turn off the heat and leave the guanciale in the pan.",
                            "Boil the spaghetti in well-salted water until al dente.",
                            "Reserve 1 cup of pasta water, then drain the pasta.",
                            "Add the pasta to the pan with the guanciale and toss to coat.",
                            "Remove the pan from heat before adding the egg–cheese mixture.",
                            "Stir quickly while adding small splashes of pasta water to create a glossy, silky sauce.",
                            "Serve immediately with extra Pecorino and black pepper."
                        )
                    )
                ), CardItem(
                    2,
                    R.drawable.avocado_toast,
                    "Avocado Toast",
                    "Simple and healthy breakfast with mashed avocado on toasted bread",
                    "7m",
                    "2",
                    "Easy",
                    arrayListOf("Breakfast", "Healthy", "Vegetarian", "Quick"),
                    RecipeItem(
                        listOf(
                            "2 slices of bread (sourdough recommended)",
                            "1 ripe avocado",
                            "1 tablespoon olive oil",
                            "1/2 lemon (for juice)",
                            "Salt to taste",
                            "Black pepper to taste",
                            "Red pepper flakes (optional)",
                            "Cherry tomatoes or a fried egg (optional toppings)"
                        ),
                        listOf(
                            "Toast the bread slices until golden and crisp.",
                            "Cut the avocado in half, remove the pit, and scoop the flesh into a bowl.",
                            "Mash the avocado lightly with a fork, keeping some texture.",
                            "Add lemon juice, olive oil, salt, and pepper, then mix to combine.",
                            "Spread the mashed avocado evenly over the toasted bread.",
                            "Top with red pepper flakes, cherry tomatoes, or a fried egg if desired.",
                            "Serve immediately."
                        )
                    )
                )
            )
        }
    }
}