package com.example.jan_aushadhifinder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jan_aushadhifinder.Medicine
import com.example.jan_aushadhifinder.sampleMedicines

@Composable
fun MedicineSearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    
    val filteredMedicines = remember(searchQuery) {
        if (searchQuery.isEmpty()) {
            sampleMedicines
        } else {
            sampleMedicines.filter {
                it.brandName.contains(searchQuery, ignoreCase = true) ||
                it.genericName.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search branded medicine (e.g. Crocin)") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(filteredMedicines) { medicine ->
                MedicineCard(medicine)
            }
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = medicine.brandName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = "Generic: ${medicine.genericName}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                }
                Text(text = medicine.dosage, style = MaterialTheme.typography.bodySmall)
            }
            
            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
            
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text(text = "Branded Price", style = MaterialTheme.typography.labelSmall)
                    Text(text = "₹${medicine.brandPrice}", style = MaterialTheme.typography.bodyLarge)
                }
                Column {
                    Text(text = "Jan-Aushadhi Price", style = MaterialTheme.typography.labelSmall)
                    Text(text = "₹${medicine.genericPrice}", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                }
                Column {
                    val savings = medicine.brandPrice - medicine.genericPrice
                    val savingsPercent = (savings / medicine.brandPrice * 100).toInt()
                    Text(text = "You Save", style = MaterialTheme.typography.labelSmall)
                    Text(text = "₹${savings.toInt()} ($savingsPercent%)", style = MaterialTheme.typography.bodyLarge, color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
