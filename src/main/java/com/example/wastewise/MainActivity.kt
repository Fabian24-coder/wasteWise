package com.example.wastewise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            listOf(Screen.Home, Screen.Schedule, Screen.Wizard, Screen.Stats, Screen.Support).forEach { screen ->
                                NavigationBarItem(
                                    selected = currentRoute == screen.route,
                                    onClick = { navController.navigate(screen.route) },
                                    label = { Text(screen.label) },
                                    icon = { /* Add Icons here */ }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
                        composable(Screen.Home.route) { /* Call your Home Composable */ }
                        composable(Screen.Schedule.route) { /* Call your Schedule Composable */ }
                        // Add other routes similarly...
                    }
                }
            }
        }
    }
}