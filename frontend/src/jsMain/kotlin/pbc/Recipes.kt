package pbc

class Recipe(
    val title: String,
    val preparationMinutes: Int,
    val cookingMinutes: Int,
    val ingredients: List<String>,
    val steps: List<String>
)

val cannelle = Recipe(
    title = "Etoiles à la canelle",
    preparationMinutes = 60,
    cookingMinutes = 5,
    ingredients = listOf(
        "2 blancs d'oeuf",
        "1 pincée de sel",
        "200g de sucre",
        "350g d'amandes moulues",
        "Une demie cuillère à soupe de cannelle",
        "70g de sucre glace",
        "Le jus d'un citron"
    ),
    steps = listOf(
        "Monter les blancs d’œufs en neige ferme avec le sel. Incorporer le sucre.",
        "Mélanger les amandes et la cannelle, ajouter aux blancs d’œufs en neige. Rassembler la pâte.",
        "Abaisser la pâte jusqu'à environ 0.5-1cm d'épaisseur. Découper des étoiles à l'emporte pièce. Faculatatif: Tremper l'emporte pièce dans du sucre pour une bordure sucrée.",
        "Déposer les biscuits sur des plaques et laisser cuire 3 à 5 min à 240°.",
        "Mélanger le sucre glace et le jus de citron pour obtenir un glaçage épais. Répartir un peu de glaçage sur les étoiles, laisser sécher."
    )
)

val sables = Recipe(
    title = "Sablés aux citrons",
    preparationMinutes = 40,
    cookingMinutes = 10,
    ingredients = listOf(
        "125 g de beurre (à température ambiante)",
        "125 g de sucre",
        "4 jaunes d’œufs",
        "250 g de farine",
        "Les zestes d'un citron",
        "1 œuf pour la dorure"
    ),
    steps = listOf(
        "Mélanger les zestes avec le sucre",
        "Introduir le beurre et les jaunes d’œufs",
        "Ajouter la farine et mélanger à nouveau. Arrêtez quand la pâte est bien mélangée et forme une boule homogène.",
        "Laisser reposer au réfrigérateur 2 heures",
        "Etaler la pâte sur un demi-centimètre d’épaisseur.",
        "Découper les sablés à l'emporte pièce",
        "Battre un œuf et dorer les sablés à l’aide d’un pinceau pour qu’ils prennent une jolie couleur et soient bien brillants une fois cuits.",
        "Enfourner à 180°C pendant 8 à 10 minutes."
    )
)


val macarons = Recipe(
    title = "Macarons aux noisettes",
    preparationMinutes = 30,
    cookingMinutes = 10,
    ingredients = listOf(
        "2 blancs d'oeuf",
        "1 pincée de sel",
        "130 g de sucre",
        "150 g de noisettes en poudre",
        "~30 noisettes entières",
    ),
    steps = listOf(
        "Monter les blancs d’œufs en neige ferme avec le sel. Incorporer le sucre.",
        "Rajouter les noisettes en poudre",
        "Confectionner des petits macarons à la poche à douille sur la plaque de cuisson",
        "Rajouter une noisette entière pour la déco",
        "Enfourner à 180°C pendant environ 10 minutes jusqu'à ce qu'ils commencent à dorer."
    )
)