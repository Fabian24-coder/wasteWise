package com.example.wastewise

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastewise.ui.theme.DarkGreenText
import com.example.wastewise.ui.theme.LightGreenBg
import com.example.wastewise.ui.theme.ButtonDarkGreen
import java.util.Locale

@Composable
fun BillingScreen(balance: Double, onPayClick: () -> Unit) {
    val bills = remember {
        listOf(
            Bill("B101", "Oct 01, 2023", 50000.0, "Paid", "Monthly Pickup Service"),
            Bill("B102", "Nov 01, 2023", 100000.0, "Pending", "Monthly Pickup Service"),
            Bill("B103", "Dec 01, 2023", 50000.0, "Overdue", "Extra Collection + Service")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Billing & Payments",
            style = MaterialTheme.typography.headlineMedium,
            color = DarkGreenText,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(24.dp))

        // Dynamic Balance Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = LightGreenBg),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccountBalanceWallet, contentDescription = null, tint = DarkGreenText)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Current Balance", style = MaterialTheme.typography.titleMedium, color = DarkGreenText)
                }
                Text(
                    text = "UGX ${String.format(Locale.US, "%,.0f", balance)}",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = DarkGreenText
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onPayClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonDarkGreen)
                ) {
                    Text("Pay via Mobile Money", fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Recent Invoices", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(bills) { bill ->
                BillItemCard(bill)
            }
        }
    }
}

@Composable
fun BillItemCard(bill: Bill) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = bill.description, fontWeight = FontWeight.Bold)
                Text(text = "Date: ${bill.date}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Surface(
                    color = when (bill.status) {
                        "Paid" -> Color(0xFFE8F5E9)
                        "Overdue" -> Color(0xFFFFEBEE)
                        else -> Color(0xFFFFF3E0)
                    },
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = bill.status,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        color = when (bill.status) {
                            "Paid" -> Color(0xFF2E7D32)
                            "Overdue" -> Color.Red
                            else -> Color(0xFFF57C00)
                        },
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
            Text(
                text = "UGX ${String.format(Locale.US, "%,.0f", bill.amount)}",
                style = MaterialTheme.typography.titleMedium,
                color = DarkGreenText,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
