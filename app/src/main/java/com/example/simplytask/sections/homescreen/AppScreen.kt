package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplytask.items.BottomNavItem
import com.example.simplytask.screens.home.HomeScreen
import com.example.simplytask.screens.map.MapScreen
import com.example.simplytask.screens.settings.SettingsScreen
import com.example.simplytask.screens.support.SupportScreen
import com.example.simplytask.screens.vehicle.CarScreen


@Composable
fun AppScreen() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        bottomBar = {
            BotNavigation(
                navController = navController
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                modifier = Modifier.padding(it),
                startDestination = BottomNavItem.Home.route
            ) {
                composable(BottomNavItem.Home.route) {
                    HomeScreen()
                }
                composable(BottomNavItem.Vehicle.route) {
                    CarScreen()
                }
                composable(BottomNavItem.Map.route) {
                    MapScreen()
                }
                composable(BottomNavItem.Support.route) {
                    SupportScreen()
                }
                composable(BottomNavItem.Settings.route) {
                    SettingsScreen()
                }
            }
        }
    }
}
