package com.example.simplytask.sections.homescreen

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytask.R
import com.example.simplytask.models.CarLockerModel
import com.example.simplytask.screens.home.HomeScreen
import com.example.simplytask.ui.theme.SimplyTaskTheme
import kotlinx.coroutines.delay

@Composable
fun CarLocker(
    carLockerModelList: List<CarLockerModel>,
    loadingProcess: Boolean = false,
    loadingFinished: () -> Unit = {},
    lockerClicked: (Int?) -> Unit = {},
    modifier: Modifier = Modifier
) {

    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
//            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Absolute.Center

    ) {
        LazyRow() {
            items(items = carLockerModelList) {
                if (it.lockerId == 2) {
                    CarLockerItem(
                        carLockerModel = it,
                        loadingProcess = loadingProcess,
                        loadingFinished = loadingFinished,
                        lockerClicked = lockerClicked,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    CarLockerItem(
                        carLockerModel = it,
                        loadingProcess = false,
                        loadingFinished = loadingFinished,
                        lockerClicked = lockerClicked,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }

}

@Composable
private fun CarLockerItem(
    carLockerModel: CarLockerModel,
    loadingProcess: Boolean = false,
    loadingFinished: () -> Unit = {},
    lockerClicked: (Int?) -> Unit = {},
    modifier: Modifier = Modifier
) {

    val unClickableClarity = if (!carLockerModel.isSelected) 1f else 0.5f
    val percentage = 1f
    val color = Color(0xFF40A0DA)
    val circleColors: List<Color> = if (loadingProcess) listOf(
        Color(0xFF759391),
        Color(0xFF34BFA6),
        Color(0xFF40A0DA)
    ) else {
        listOf(
            Color(0xFF000000),
            Color(0xFF000000),
            Color(0xFF000000),
        )
    }

    val strokeWidth = 4.dp
    val animDuration = 5000
    val animeDelay = 0

    val curPercentage = animateFloatAsState(
        targetValue = if (loadingProcess) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animeDelay
        ), label = ""
    ) {
        if (it == 1f) {
            loadingFinished()
        }
    }

//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 18.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically
//
//        ) {
    Column(
        modifier = modifier,
//            verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box() {

            Box(modifier = Modifier
                .size(68.dp).padding(8.dp)
                .clip(CircleShape),contentAlignment = Alignment.Center) {

                Canvas(modifier = Modifier
                    .size(68.dp)
                    .alpha(unClickableClarity)
                    .clickable(carLockerModel.isClickable) {
                        if (carLockerModel.lockerId == 1 || carLockerModel.lockerId == 2)
                            lockerClicked(carLockerModel.lockerId)
                    }) {
                    drawArc(
                        brush = Brush.sweepGradient(circleColors),
                        0f,
                        sweepAngle = if (loadingProcess) {
                            360 * curPercentage.value
                        } else {
                            360f
                        },
                        useCenter = false,
                        style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }
            }
            Icon(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .alpha(unClickableClarity)
                    .align(Alignment.Center),
                imageVector = ImageVector.vectorResource(
                    carLockerModel.lockerIcon ?: R.drawable.ic_locker_locked
                ),
                contentDescription = "Icon Locker Locked",
                tint = Color.Black
            )
        }
        Text(
            text = stringResource(carLockerModel.lockerName ?: R.string.top_bar_car_name),
            fontSize = 12.sp,
            lineHeight = 18.sp,
            fontWeight = FontWeight(500),
            color = Color(0xFF020202),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .width(56.dp)
                .padding(top = 1.dp)
                .alpha(unClickableClarity)
                .align(Alignment.CenterHorizontally)
            ,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SimplyTaskTheme {
        HomeScreen()
    }
}