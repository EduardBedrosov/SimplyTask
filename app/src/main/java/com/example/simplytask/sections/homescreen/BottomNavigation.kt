package com.example.simplytask.sections.homescreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.simplytask.R
import com.example.simplytask.items.BottomNavItem

@Composable
fun BottomNavigation(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color(R.color.white)),
        containerColor = MaterialTheme.colorScheme.onTertiary
    ) {

        val currentBackStackEntry by navController.currentBackStackEntryAsState()
        val destinationRoute = currentBackStackEntry?.destination?.route

        val bottomNavBarItems = navigationBarItemsList()


        bottomNavBarItems.forEach { bottomNavItem ->
            NavBarItem(
                modifier = Modifier.weight(1f),
                bottomNavItem = bottomNavItem,
                isSelected = destinationRoute == bottomNavItem.route
            ) { navClicked ->
                navController.navigate(navClicked.route)
            }
        }
    }

}
@Composable
fun NavBarItem(
    modifier: Modifier = Modifier,
    bottomNavItem: BottomNavItem,
    isSelected: Boolean,
    navClicked: (BottomNavItem) -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(4.dp, 0.dp, 8.dp, 4.dp)
            .clickable { navClicked(bottomNavItem) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {
        val fontWeight = if (isSelected) 700 else 400
        val color =
            if (!isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary


//            val fontFamily =
//                if (isSelected) FontStyle(Font(R.font.nissan_brand_bold)) else FontFamily(Font(R.font.nissan_brand_regular))

        Divider(
            modifier = Modifier
                .height(4.dp)
                .padding(top = 2.dp),
            color = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent
        )

        Icon(
            modifier = Modifier.size(28.dp),
            painter = painterResource(bottomNavItem.icon),
            contentDescription = "Nav Icon",
            tint = color
        )

        Text(
            text = bottomNavItem.label,
            fontSize = 12.sp,
            lineHeight = 16.sp,
//            fontFamily = FontFamily(Font(familyName = )),
            fontWeight = FontWeight(fontWeight),
            textAlign = TextAlign.Center,
            color = color,

//                fontFamily = fontFamily
        )
    }

}

private fun navigationBarItemsList(): List<BottomNavItem> {
    return listOf(
        BottomNavItem.Home,
        BottomNavItem.Vehicle,
        BottomNavItem.Map,
        BottomNavItem.Support,
        BottomNavItem.Settings,
    )
}
