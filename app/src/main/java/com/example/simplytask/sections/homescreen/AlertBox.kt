package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplytask.R
import com.example.simplytask.screens.home.HomeScreenViewModel

@Composable
fun AlertBox(
    viewModel: HomeScreenViewModel,
    loadingDelay: () -> Unit = {}
    ) {
    Column {
        val openDialog = remember { mutableStateOf(true)  }

//        Button(onClick = {
//            openDialog.value = true
//        }) {
//            Text("Click me")
//        }

        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                    viewModel.lockedCarState()
                },
                title = {
                    Text(text = "Are you Sure?")
                },
                text = {
                    Text("This action will remotely unlock your vehicle.")
                },
                confirmButton = {
                    Button(modifier = Modifier.shadow(elevation = 8.dp,
                        spotColor = Color(0x26000000), ambientColor = Color(0x26000000))

                        .width(47.dp)
                        .height(30.dp)
                        .background(color = Color(0xFF40A0DA), shape = RoundedCornerShape(size = 3.dp))
                        .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp),
                        onClick = {
                            openDialog.value = false
                           viewModel.unlockingCar()
                        }) {
                        Text(modifier = Modifier,
                            text = "Yes")
                    }
                },
                dismissButton = {
                    TextButton(
                        modifier = Modifier.shadow(elevation = 8.dp,
                        spotColor = Color(0x26000000), ambientColor = Color(0x26000000))
                    .width(47.dp)
                    .height(30.dp)
                    .background(color = Color(0xFF40A0DA), shape = RoundedCornerShape(size = 3.dp))
                    .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp),
                        onClick = {
                            openDialog.value = false
                            viewModel.lockedCarState()
                        }) {
                        Text(modifier = Modifier.shadow(elevation = 10.dp).background(Color.Unspecified),
                            fontSize = 14.sp,
                            lineHeight = 22.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            text ="No")
                    }
                }
            )
        }
    }

}
