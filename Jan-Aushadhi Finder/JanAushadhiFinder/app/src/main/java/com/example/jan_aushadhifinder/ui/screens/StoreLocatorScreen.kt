package com.example.jan_aushadhifinder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.jan_aushadhifinder.Store
import com.example.jan_aushadhifinder.sampleStores
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun StoreLocatorScreen() {
    val bangalore = LatLng(12.9716, 77.5946)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(bangalore, 12f)
    }

    var selectedStore by remember { mutableStateOf<Store?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                sampleStores.forEach { store ->
                    Marker(
                        state = MarkerState(position = store.location),
                        title = store.name,
                        snippet = store.address,
                        onClick = {
                            selectedStore = store
                            false
                        }
                    )
                }
            }
            
            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.9f))
            ) {
                Text(
                    text = "Showing Stores within 10km",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Text(
                    text = "Nearby Jan-Aushadhi Kendras",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            items(sampleStores) { store ->
                StoreListItem(store)
            }
        }
    }
}

@Composable
fun StoreListItem(store: Store) {
    var showStockDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = store.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(text = store.address, style = MaterialTheme.typography.bodySmall)
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = if (store.isOpen) "Open Now" else "Closed",
                        color = if (store.isOpen) Color(0xFF2E7D32) else Color.Red,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = store.distance, style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Row(modifier = Modifier.padding(top = 8.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = { /* Simulated navigation */ },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Icon(Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("Directions", style = MaterialTheme.typography.labelLarge)
                }
                OutlinedButton(
                    onClick = { showStockDialog = true },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text("Check Stock", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }

    if (showStockDialog) {
        AlertDialog(
            onDismissRequest = { showStockDialog = false },
            title = { Text("Check Medicine Stock") },
            text = { Text("Would you like to send a simulated stock availability request to ${store.name}?") },
            confirmButton = {
                TextButton(onClick = { showStockDialog = false }) {
                    Text("Send Request")
                }
            },
            dismissButton = {
                TextButton(onClick = { showStockDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
