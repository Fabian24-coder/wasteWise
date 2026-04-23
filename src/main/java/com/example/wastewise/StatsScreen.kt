package com.example.wastewise

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*

@Composable
fun StatsScreen() {
    Column {
        Text("Impact Dashboard", style = MaterialTheme.typography.titleLarge)
        Text("Total Diverted: 78.4kg")
    }
}