package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytask.R

@Composable
fun Weather() {
    Row {
        Icon(
            modifier = Modifier
                .padding(start = 4.dp)
                .width(24.dp)
                .height(24.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_weather),
            contentDescription = "Locker",
            tint = MaterialTheme.colorScheme.onTertiary
        )
        Text(
            text = stringResource(R.string.degree),
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = MaterialTheme.colorScheme.onTertiary,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(start = 2.dp, top = 4.dp)
        )
    }
}