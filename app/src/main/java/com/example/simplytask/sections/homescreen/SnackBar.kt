package com.example.simplytask.sections.homescreen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.simplytask.R
import com.example.simplytask.screens.home.HomeScreenState


@Composable
fun SnackBar(
    contextForToast: Context?,
    homeScreenState: HomeScreenState
) {
    Snackbar(
        modifier = Modifier
            .padding(all = 8.dp),
        action = {
            TextButton(
                onClick = {
                    Toast.makeText(
                        contextForToast,
                        "Action Click",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            ) {
                if (homeScreenState.snackBarModel?.isAccepted == true) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.icon_confirmation),
                        contentDescription = "confirmation",
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                            .padding(1.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }
    ) {
        Text(text = homeScreenState.snackBarModel?.message ?: " ")
    }
}
