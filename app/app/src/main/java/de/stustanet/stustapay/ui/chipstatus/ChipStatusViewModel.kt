package de.stustanet.stustapay.ui.chipstatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.stustanet.stustapay.model.NfcState
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import de.stustanet.stustapay.util.combine

@HiltViewModel
class ChipStatusViewModel @Inject constructor(
    nfcState: NfcState
) : ViewModel() {
    private val _uid = nfcState.uid
    private val _scanRequest = nfcState.scanRequest
    private val _writeRequest = nfcState.writeRequest
    private val _protectRequest = nfcState.protectRequest

    private val _enableDebugCard = nfcState.enableDebugCard

    private val _chipDataReady = nfcState.chipDataReady
    private val _chipCompatible = nfcState.chipCompatible
    private val _chipAuthenticated = nfcState.chipAuthenticated
    private val _chipProtected = nfcState.chipProtected
    private val _chipUid = nfcState.chipUid
    private val _chipContent = nfcState.chipContent

    val uiState: StateFlow<ChipStatusUiState> = combine(
        _scanRequest,
        _writeRequest,
        _protectRequest,
        _enableDebugCard,
        _chipDataReady,
        _chipCompatible,
        _chipAuthenticated,
        _chipProtected,
        _chipUid,
        _chipContent
    ) { scanRequest, writeRequest, protectRequest, enableDebugCard, dataReady, compatible, authenticated, protected, uid, content ->
        ChipStatusUiState (
            scanRequest = scanRequest,
            writeRequest = writeRequest,
            protectRequest = protectRequest,
            enableDebugCard = enableDebugCard,
            dataReady = dataReady,
            compatible = compatible,
            authenticated = authenticated,
            protected = protected,
            uid = uid,
            content = content
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ChipStatusUiState()
    )

    fun scan(req: Boolean) {
        _scanRequest.update { req }
    }

    fun write(req: Boolean) {
        _writeRequest.update { req }
    }

    fun protect(req: Boolean) {
        _protectRequest.update { req }
    }

    fun debug(req: Boolean) {
        _enableDebugCard.update { req }
    }

    fun setContent(content: String) {
        _chipContent.update { content }
    }
}

data class ChipStatusUiState(
    val uid: ULong = 0uL,
)
