package com.example.wastewise

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastewise.ui.theme.DarkGreenText

@Composable
fun HistoryScreen() {
    val historyItems = listOf(
        HistoryItem("1", "Monthly Service Fee", "Oct 01, 2023", "$25.00", "Paid", "Payment"),
        HistoryItem("2", "Recyclables Collection", "Sep 28, 2023", null, "Completed", "Collection"),
        HistoryItem("3", "Organic Waste Collection", "Sep 25, 2023", null, "Completed", "Collection"),
        HistoryItem("4", "Extra Pickup - Bulk", "Sep 20, 2023", "$15.00", "Paid", "Payment"),
        HistoryItem("5", "General Trash", "Sep 18, 2023", null, "Missed", "Collection")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Activity History",
            style = MaterialTheme.typography.headlineMedium,
            color = DarkGreenText
        )
        Text(
            text = "Review your past collections and payments.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(historyItems) { item ->
                HistoryCard(item)
            }
        }
    }
}

@Composable
fun HistoryCard(item: HistoryItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (item.type == "Payment") Icons.Default.Payments else Icons.Default.Delete,
                contentDescription = null,
                tint = if (item.status == "Missed") Color.Red else DarkGreenText,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.title, fontWeight = FontWeight.Bold)
                Text(text = item.date, fontSize = 12.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                if (item.amount != null) {
                    Text(text = item.amount, fontWeight = FontWeight.Bold, color = DarkGreenText)
                }
                Badge(
                    containerColor = when(item.status) {
                        "Paid", "Completed" -> Color(0xFFE8F5E9)
                        "Missed" -> Color(0xFFFFEBEE)
                        else -> Color.LightGray
                    },
                    contentColor = when(item.status) {
                        "Paid", "Completed" -> Color(0xFF2E7D32)
                        "Missed" -> Color.Red
                        else -> Color.DarkGray
                    }
                ) {
                    Text(item.status, modifier = Modifier.padding(horizontal = 4.dp), fontSize = 10.sp)
                }
            }
        }
    }
}
