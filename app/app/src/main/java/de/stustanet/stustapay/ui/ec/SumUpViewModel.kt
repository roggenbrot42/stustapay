package de.stustanet.stustapay.ui.ec

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.stustanet.stustapay.ec.SumUpHandler
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SumUpViewModel @Inject constructor(
    sumUpHandler : SumUpHandler
) : ViewModel() {

//    private val _uid = sumUpHandler.uid
//    private val _scanRequest = sumUpHandler.scanRequest

//    val uiState: StateFlow<SumUpStatusUiState> = _uid.map { uid ->
//        SumUpStatusUiState (
//            uid = uid
//        )
//    }.stateIn(`
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = SumUpStatusUiState(uid = 0uL)
//    )
//
//    fun scan(req: Boolean) {
//        _scanRequest.update { req }
//    }
}

data class SumUpStatusUiState(
    val uid: ULong = 0uL,
)