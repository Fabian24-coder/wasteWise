package com.example.wastewise

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScheduleScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Next Collection", style = MaterialTheme.typography.headlineMedium)
        // Add the rest of your scheduling UI here...
        Button(onClick = { /* Handle Click */ }) {
            Text("Schedule Pickup")
        }
    }
}