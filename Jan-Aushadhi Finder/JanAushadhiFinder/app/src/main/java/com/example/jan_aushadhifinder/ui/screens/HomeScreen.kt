package com.example.jan_aushadhifinder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jan_aushadhifinder.sampleMedicines

@Composable
fun HomeScreen(navController: NavController) {
    val totalPotentialSavings = sampleMedicines.sumOf { it.brandPrice - it.genericPrice }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Jan aushadhi Finder",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Potential Savings", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "₹${totalPotentialSavings.toInt()}",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "If you switch all common prescriptions to Generic", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = { navController.navigate("search") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search Medicines")
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        OutlinedButton(
            onClick = { navController.navigate("stores") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Find Nearest Store")
        }
    }
}
