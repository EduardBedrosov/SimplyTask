package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytask.R

@Composable
fun Speedometer(speedRange: Int? = null) {

    Column(modifier = Modifier.padding(top = 26.dp)) {
        Text(
            text = stringResource(R.string.est_range),
            fontSize = 14.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight(500),
            color = Color(R.color.primary_grey),
            modifier = Modifier.padding(start = 6.dp)
        )
        Row (
            horizontalArrangement = Arrangement.Absolute.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.padding(start = 4.dp)
        ){
            Text(
//                text = speedRange.toString(),
                text =  "120",
                fontSize = 40.sp,
                lineHeight = 32.sp,
                fontWeight = FontWeight(700)
            )
            Text(
                text = stringResource(R.string.speed_value),
                fontSize = 18.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                modifier = Modifier.padding( bottom = 6.dp)
            )

        }

    }
}