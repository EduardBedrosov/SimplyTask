package com.example.simplytask.models

data class CarLockerModel (
    val lockerId: Int?,
    val lockerName : Int?,
    val lockerIcon : Int?,
    var isSelected: Boolean = false,
    var isClickable: Boolean = false
)