package com.example.wastewise

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wastewise.ui.theme.ButtonDarkGreen
import com.example.wastewise.ui.theme.DarkGreenText
import com.example.wastewise.ui.theme.LightGreenBg
import kotlinx.coroutines.delay
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(amount: Double, onBack: () -> Unit, onPaymentSuccess: () -> Unit) {
    var phoneNumber by remember { mutableStateOf("") }
    var selectedProvider by remember { mutableStateOf("MTN") }
    var isProcessing by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val formattedAmount = String.format(Locale.US, "%,.0f", amount)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mobile Money Payment", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mobile Money Icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(LightGreenBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.PhoneAndroid,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = DarkGreenText
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            Text("Total Amount", color = Color.Gray, fontSize = 14.sp)
            Text(
                "UGX $formattedAmount",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = DarkGreenText
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                "Select Provider",
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProviderOption(
                    name = "MTN",
                    isSelected = selectedProvider == "MTN",
                    onClick = { selectedProvider = "MTN" },
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFFCC00)
                )
                ProviderOption(
                    name = "Airtel",
                    isSelected = selectedProvider == "Airtel",
                    onClick = { selectedProvider = "Airtel" },
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFFF0000)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { 
                    if (it.length <= 10) phoneNumber = it 
                    errorMessage = null
                },
                label = { Text("Phone Number") },
                placeholder = { Text("07XXXXXXXX") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            if (errorMessage != null) {
                Text(errorMessage!!, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp).align(Alignment.Start))
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = {
                    if (phoneNumber.length < 10) {
                        errorMessage = "Enter valid 10-digit number"
                    } else {
                        isProcessing = true
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PickupTeal),
                enabled = !isProcessing && amount > 0,
                shape = RoundedCornerShape(12.dp)
            ) {
                if (isProcessing) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Pay UGX $formattedAmount", fontWeight = FontWeight.Bold)
                }
            }

            Text(
                "A USSD prompt will be sent to $phoneNumber",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 16.dp)
            )

            if (isProcessing) {
                LaunchedEffect(Unit) {
                    delay(3000)
                    onPaymentSuccess()
                }
            }
        }
    }
}

@Composable
fun ProviderOption(name: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier, color: Color) {
    Surface(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(width = if (isSelected) 2.dp else 1.dp, color = if (isSelected) DarkGreenText else Color.LightGray, shape = RoundedCornerShape(12.dp)),
        color = if (isSelected) color.copy(alpha = 0.1f) else Color.White
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(name, fontWeight = FontWeight.Bold, color = if (isSelected) DarkGreenText else Color.Black)
        }
    }
}
