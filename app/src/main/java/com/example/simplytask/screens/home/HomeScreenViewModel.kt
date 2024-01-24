package com.example.simplytask.screens.home

import androidx.lifecycle.ViewModel
import com.example.simplytask.R
import com.example.simplytask.models.CarLockerModel
import com.example.simplytask.models.SnackBarModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {


    private val carLockerModelList = getVehicleModel()
    private val snackBarDefaultModel = getSnackBarModel()

    private val _state: MutableStateFlow<HomeScreenState> = MutableStateFlow(
        HomeScreenState(
            carLockerModelList = carLockerModelList,
            snackBarModel = null,
            loadingProcess = false,
            showSnackBar = false,
            showAlertBox = false
        )
    )

    val state: StateFlow<HomeScreenState>
        get() = _state.asStateFlow()


    fun lockPickerClicked(lockersId: Int?) {

        disableActiveLocks()

        if (lockersId == 1){
            lockedCarState()
        }else{
            updateState(
//                carLockerModelList = _state.value.carLockerModelList,
                showAlertBox = true
            )
        }

    }

    fun unlockingCar() {

        updateState(
//            carLockerModelList = _state.value.carLockerModelList,
            showSnackBar = true,
            loadingProcess = true,
            snackBarModel = snackBarDefaultModel[0]
        )
    }

    fun unLockedCarState() {
        changeActiveLock()
        updateState(
            carLockerModelList = _state.value.carLockerModelList,
            showSnackBar = true,
            snackBarModel = snackBarDefaultModel[1]
        )
    }

    fun lockedCarState() {
        defaultState()
    }


    private fun updateState(
        carLockerModelList: List<CarLockerModel> = _state.value.carLockerModelList,
        snackBarModel: SnackBarModel? = null,
        showSnackBar: Boolean = false,
        loadingProcess: Boolean = false,
        showAlertBox: Boolean = false

    ) {
        _state.update {
            it.copy(
                carLockerModelList = carLockerModelList,
                snackBarModel = snackBarModel,
                loadingProcess = loadingProcess,
                showSnackBar = showSnackBar,
                showAlertBox = showAlertBox
            )
        }

    }

    private fun defaultState() {
        defaultActiveLocks()
        updateState()
    }

    private fun getSnackBarModel(): List<SnackBarModel> {
        val tempStringMessage: String = "Waiting Ariya to unlock"
        val tempStringMessage2: String = "Ariya unlocked"
        return listOf(
            SnackBarModel(tempStringMessage, false),
            SnackBarModel(tempStringMessage2, true)
        )
    }

    private fun getVehicleModel(): List<CarLockerModel> {
        return listOf(
            CarLockerModel(
                lockerId = 1,
                lockerName = R.string.lock,
                lockerIcon = R.drawable.ic_locker,
                isSelected = true
            ),
            CarLockerModel(
                lockerId = 2,
                lockerName = R.string.unlock,
                lockerIcon = R.drawable.ic_locker_unlocked,
                isClickable = true
            ),
            CarLockerModel(
                lockerId = 3,
                lockerName = R.string.climate,
                lockerIcon = R.drawable.ic_fan,
                isClickable = true
            ),
            CarLockerModel(
                lockerId = 4,
                lockerName = R.string.charge,
                lockerIcon = R.drawable.ic_charge,
                isClickable = true
            ),
            CarLockerModel(
                lockerId = 5,
                lockerName = R.string.lights,
                lockerIcon = R.drawable.ic_outline,
                isClickable = true
            )
        )
    }

    private fun changeActiveLock(carLockerModel: List<CarLockerModel> = emptyList(), lockersId: Int? = null) {
        _state.value.carLockerModelList.forEach {
            it.isSelected = false
            it.isClickable = true
        }
        _state.value.carLockerModelList[1].isSelected = true
        _state.value.carLockerModelList[1].isClickable = false
    }

    private fun disableActiveLocks() {
        _state.value.carLockerModelList.forEach {
            it.isClickable = false
        }
    }
    private fun defaultActiveLocks() {
        _state.value.carLockerModelList.forEach {
            it.isSelected = false
            it.isClickable = true
        }
        _state.value.carLockerModelList[0].isClickable = false
        _state.value.carLockerModelList[0].isSelected = true
    }
}