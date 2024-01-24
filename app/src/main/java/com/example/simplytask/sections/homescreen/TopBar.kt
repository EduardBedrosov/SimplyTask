package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytask.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = Modifier.padding(top = 14.dp),
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_locker_locked),
                    contentDescription = "Locker",
                    tint = MaterialTheme.colorScheme.onTertiary
                )

                Text(
                    text = stringResource(R.string.top_bar_car_name).uppercase(),
                    fontWeight = FontWeight(400),
                    maxLines = 1,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onTertiary,
                    fontFamily = FontFamily(
                        Font(R.font.nissan_regular)),
                )
            }
        },
        navigationIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.live_assist),
                contentDescription = "Contact Support",
                modifier = Modifier
                    .padding(20.dp, 1.dp, 1.dp, 1.dp)
                    .width(28.dp)
                    .height(28.dp),
                tint = MaterialTheme.colorScheme.onTertiary
            )
//            }
        },
        actions = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
                contentDescription = "Notifications",
                modifier = Modifier
                    .padding(1.dp, 1.dp, 12.dp, 1.dp)
                    .clickable { },
                tint = MaterialTheme.colorScheme.onTertiary
            )
//            }
        },
        scrollBehavior = scrollBehavior,
    )
}