package com.example.simplytask.sections.homescreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.simplytask.items.BottomNavItem
import com.example.simplytask.screens.vehicle.CarScreen
import com.example.simplytask.screens.home.HomeScreen
import com.example.simplytask.screens.map.MapScreen
import com.example.simplytask.screens.settings.SettingsScreen
import com.example.simplytask.screens.support.SupportScreen


@Composable
fun BottomNavBar() {
    val navController = rememberNavController()
    Scaffold(
//        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                val bottomNavBarItems = listOf(
                    BottomNavItem.Home,
                    BottomNavItem.Vehicle,
                    BottomNavItem.Map,
                    BottomNavItem.Support,
                    BottomNavItem.Settings,
                )

                bottomNavBarItems.forEach { screen ->

                    var isNavSelected by remember { mutableStateOf(true) }

                    // surfaceColor will be updated gradually from one color to the other
                    val surfaceColor by animateColorAsState(
                        if (isNavSelected) Color.Black else Color.Red,
                        label = "",
                    )
                    BottomNavigationItem(
                        modifier = Modifier
                            .shadow(
                                elevation = 10.dp,
                                spotColor = Color(0x26000000),
                                ambientColor = Color(0x26000000)
                            )
                            .padding(1.dp)
                            .background(color = Color(0xFF749C35)),
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(screen.icon),
                                contentDescription = screen.route,
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp)
                                    .padding(1.dp),
                                tint = surfaceColor
                            )
                        },
                        label = {
                            Text(
                                screen.label,
                                modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                ),
                                fontWeight = if (!isNavSelected) {
                                    FontWeight(700)
                                } else {
                                    FontWeight(400)
                                },
                                color = Color(0xFF6A7081),
                                textAlign = TextAlign.Center
                            )
                        },
                        selected = currentDestination?.hierarchy?.any {
                            isNavSelected =
                                it.route != screen.route;
                            it.route == screen.route
                        } == true,
                        onClick = {
                            isNavSelected = !isNavSelected
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController,
                modifier = Modifier.padding(innerPadding),
                startDestination = BottomNavItem.Home.route
            ) {
                composable(BottomNavItem.Home.route) { HomeScreen() }
                composable(BottomNavItem.Vehicle.route) { CarScreen() }
                composable(BottomNavItem.Vehicle.route) { MapScreen() }
                composable(BottomNavItem.Vehicle.route) { SupportScreen() }
                composable(BottomNavItem.Vehicle.route) { SettingsScreen() }
            }
        }
    }

}