package com.example.simplytask.sections.homescreen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.simplytask.items.BottomNavItem

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier) {

    NavigationBar(
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.White
    ) {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val bottomNavBarItems = listOf(
            BottomNavItem.Home,
            BottomNavItem.Vehicle,
            BottomNavItem.Map,
            BottomNavItem.Support,
            BottomNavItem.Settings,
        )

        bottomNavBarItems.forEach {
            BottomNavItems(it, currentRoute == it.route, Modifier.weight(1f)) { selected ->
                navController.navigate(selected.route)
            }
        }
    }
}

    @Composable
    fun BottomNavItems(
        bottomNavItem: BottomNavItem, isSelected: Boolean,
        modifier: Modifier = Modifier,
        onClick: (BottomNavItem) -> Unit = {},
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .padding(2.dp, 0.dp, 8.dp, 2.dp)
                .clickable { onClick(bottomNavItem) }) {
            val color =
                if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary

//            val fontFamily =
//                if (isSelected) FontStyle(Font(R.font.nissan_brand_bold)) else FontFamily(Font(R.font.nissan_brand_regular))

            Divider(
                modifier = Modifier.height(3.dp),
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
            )

            Icon(
                modifier = Modifier.size(26.dp),
                painter = painterResource(id = bottomNavItem.icon),
                contentDescription = "image",
                tint = color
            )

            Text(
                text = bottomNavItem.route,
                fontSize = 12.sp,
                color = color,
//                fontFamily = fontFamily
            )
        }
    }
