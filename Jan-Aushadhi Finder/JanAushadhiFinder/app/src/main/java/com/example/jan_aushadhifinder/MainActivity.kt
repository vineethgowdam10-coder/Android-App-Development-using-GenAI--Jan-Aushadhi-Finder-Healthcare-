package com.example.jan_aushadhifinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jan_aushadhifinder.ui.screens.HomeScreen
import com.example.jan_aushadhifinder.ui.screens.MedicineSearchScreen
import com.example.jan_aushadhifinder.ui.screens.RemindersScreen
import com.example.jan_aushadhifinder.ui.screens.StoreLocatorScreen
import com.example.jan_aushadhifinder.ui.theme.JanAushadhiFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JanAushadhiFinderTheme {
                MainScreen()
            }
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: @Composable () -> Unit) {
    object Home : Screen("home", "Home", { Icon(Icons.Default.Home, contentDescription = null) })
    object Search : Screen("search", "Search", { Icon(Icons.Default.Search, contentDescription = null) })
    object Stores : Screen("stores", "Stores", { Icon(Icons.Default.LocationOn, contentDescription = null) })
    object Reminders : Screen("reminders", "Reminders", { Icon(Icons.Default.Notifications, contentDescription = null) })
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Home,
        Screen.Search,
        Screen.Stores,
        Screen.Reminders
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = screen.icon,
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding)) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Search.route) { MedicineSearchScreen() }
            composable(Screen.Stores.route) { StoreLocatorScreen() }
            composable(Screen.Reminders.route) { RemindersScreen() }
        }
    }
}
