package com.example.wastewise

sealed class Screen(val route: String, val label: String) {
    object Home : Screen("home", "Home")
    object Schedule : Screen("schedule", "Schedule")
    object Wizard : Screen("wizard", "Wizard")
    object Stats : Screen("stats", "Stats")
    object Support : Screen("support", "Support")
}

data class WasteItem(
    val name: String,
    val category: String,
    val instructions: String
)