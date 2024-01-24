package com.example.simplytask.screens.home

import com.example.simplytask.models.CarLockerModel
import com.example.simplytask.models.SnackBarModel

data class HomeScreenState(
    val carLockerModelList: List<CarLockerModel> = emptyList(),
    val snackBarModel: SnackBarModel? = null,
    val showSnackBar: Boolean = false,
    val loadingProcess: Boolean = false,
    val showAlertBox: Boolean = false,
)