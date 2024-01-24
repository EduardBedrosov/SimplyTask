package com.example.simplytask.items

import com.example.simplytask.R

sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
    data object Home : BottomNavItem("home", R.drawable.group_451, "Home")
    data object Vehicle : BottomNavItem("vehicle", R.drawable.group, "Vehicle")
    data object Map : BottomNavItem("map", R.drawable.icn_poi, "Map")
    data object Support : BottomNavItem("support", R.drawable.live_assist, "Support")
    data object Settings : BottomNavItem("settings", R.drawable.gear, "Settings")
}