package com.example.jan_aushadhifinder.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

data class Reminder(
    val id: Int,
    val medicineName: String,
    val nextRefillDate: LocalDate,
    val dosage: String
)

@Composable
fun RemindersScreen() {
    var reminders by remember {
        mutableStateOf(
            listOf(
                Reminder(1, "Glycomet 500mg", LocalDate.now().plusDays(5), "1 tab daily"),
                Reminder(2, "Telma-H", LocalDate.now().plusDays(12), "1 tab daily")
            )
        )
    }

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Reminder")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            Text(
                text = "Medicine Refill Reminders",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (reminders.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No reminders set. Add one to track your refills.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(reminders) { reminder ->
                        ReminderCard(reminder, onDelete = { 
                            reminders = reminders.filter { it.id != reminder.id }
                        })
                    }
                }
            }
        }
    }

    if (showAddDialog) {
        // Simple mock add dialog
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Add Refill Reminder") },
            text = { Text("Enter medicine name and refill date (Mock UI)") },
            confirmButton = {
                TextButton(onClick = { 
                    val newId = (reminders.maxOfOrNull { it.id } ?: 0) + 1
                    reminders = reminders + Reminder(newId, "New Medicine", LocalDate.now().plusDays(30), "As prescribed")
                    showAddDialog = false 
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun ReminderCard(reminder: Reminder, onDelete: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = reminder.medicineName, style = MaterialTheme.typography.titleMedium)
                Text(text = "Next refill: ${reminder.nextRefillDate}", style = MaterialTheme.typography.bodySmall)
                Text(text = reminder.dosage, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}
