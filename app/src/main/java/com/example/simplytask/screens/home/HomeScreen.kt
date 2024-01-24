package com.example.simplytask.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplytask.R
import com.example.simplytask.sections.homescreen.AlertBox
import com.example.simplytask.sections.homescreen.CarLocker
import com.example.simplytask.sections.homescreen.Location
import com.example.simplytask.sections.homescreen.SnackBar
import com.example.simplytask.sections.homescreen.Speedometer
import com.example.simplytask.sections.homescreen.TopBar
import com.example.simplytask.sections.homescreen.Weather
import com.example.simplytask.ui.theme.SimplyTaskTheme
import kotlinx.coroutines.delay


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    homeViewModel: HomeScreenViewModel = viewModel()
) {

    val homeScreenState: HomeScreenState by homeViewModel.state.collectAsState()
    var snackBarDelay by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = homeScreenState.showSnackBar, block = {
        snackBarDelay = homeScreenState.showSnackBar
        delay(8500)
        snackBarDelay = false
    })

    val contextForToast = LocalContext.current.applicationContext

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar() },
        snackbarHost = {
            if (snackBarDelay) {
                SnackBar(contextForToast, homeScreenState)
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
//            Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.morning1),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp, 80.dp, 1.dp, 1.dp)
            ) {

                Speedometer()

                Row(
                    modifier = Modifier.padding(top = 24.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    Weather()
                    Location()
                }

            }
            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.car_background2),
                    contentDescription = "Background Car",
                    modifier = Modifier
                        .offset(184.dp, 226.dp)
                        .width(314.dp)
                        .height(172.dp),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .padding(top = 260.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    CarLocker(
                        carLockerModelList = homeScreenState.carLockerModelList,
                        loadingProcess = homeScreenState.loadingProcess,
                        lockerClicked = {
                            homeViewModel.lockPickerClicked(it)
                        },
                        loadingFinished = {
                            homeViewModel.unLockedCarState()
                        }
                    )
                }
            }
        }

        if (homeScreenState.showAlertBox) {
            AlertBox(homeViewModel)
        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SimplyTaskTheme {
        HomeScreen()
    }
}