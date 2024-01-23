package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytask.R

@Composable
fun Location() {
    Text(
//        text = ("${R.string.city_name}., ${R.string.state_name}").toString(),
        text = "${stringResource(id = R.string.city_name)}, ${stringResource(id = R.string.state_name)}",
        fontSize = 14.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight(500),
        modifier = Modifier.padding(start = 4.dp, bottom = 1.dp)
    )
}