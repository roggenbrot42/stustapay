package de.stustanet.stustapay.nfc

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NfcState @Inject constructor() {
    val scanRequest = MutableStateFlow(false)

    val uid = MutableStateFlow(0uL)
}