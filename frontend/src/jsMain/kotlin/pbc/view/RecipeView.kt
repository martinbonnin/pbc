package pbc.view

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.*
import pbc.Recipe
import pbc.TextStyles

@Composable
fun RecipeView(recipe: Recipe) {
    H1(attrs = { classes(TextStyles.titleText) }) {
        Text(recipe.title)
    }

    H2 {
        Text("Préparation: ${recipe.preparationMinutes} min - Cuisson: ${recipe.cookingMinutes} min")
    }

    H3 {
        Text("Ingrédients")
    }

    recipe.ingredients.forEach {
        Div {
            Text(it)
        }
    }

    H3 {
        Text("Préparation")
    }

    recipe.steps.forEach {
        Div {
            Text(it)
        }
    }
}