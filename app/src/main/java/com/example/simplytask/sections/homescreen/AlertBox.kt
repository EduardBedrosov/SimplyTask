package com.example.simplytask.sections.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplytask.R
import com.example.simplytask.screens.home.HomeScreenViewModel

@Composable
fun AlertBox(
    viewModel: HomeScreenViewModel,
//    loadingDelay: () -> Unit = {}
) {
    Column() {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                    viewModel.lockedCarState()
                },
                title = {
                    Text(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(400),
                        fontFamily = FontFamily(Font(R.font.nissan_regular)),
                        text = "Are you Sure?"
                    )
                },
                text = {
                    Text(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(500),
                        fontFamily = FontFamily(Font(R.font.nissan_regular)),
                        text = "This action will remotely unlock your vehicle."
                    )
                },
                confirmButton = {

                    TextButton(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 4.dp,
                            end = 12.dp,
                            bottom = 4.dp
                        ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF40A0DA)),
                        onClick = {
                            openDialog.value = false
                            viewModel.unlockingCar()
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    {
                        Text(
                            text = "Yes",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.nissan_regular)),
                            fontWeight = FontWeight(700)
                        )
                    }

                },
                dismissButton = {

                    TextButton(
                        modifier = Modifier.padding(
                            start = 12.dp,
                            top = 4.dp,
                            end = 12.dp,
                            bottom = 4.dp
                        ),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = {
                            openDialog.value = false
                            viewModel.lockedCarState()
                        },
                        shape = RoundedCornerShape(4.dp)
                    )
                    {
                        Text(
                            text = "No",
                            color = Color(0xFF40A0DA),
                            fontFamily = FontFamily(Font(R.font.nissan_regular)),
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700)
                        )
                    }
                }
            )
        }
    }
}
