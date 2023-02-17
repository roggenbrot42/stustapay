package de.stustanet.stustapay.ui.chipstatus

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import de.stustanet.stustapay.ec.SumUpHandler
import de.stustanet.stustapay.ui.ec.SumUpViewModel

@Preview
@Composable
fun SumUpView(viewModel: SumUpViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//        viewModel.scan(true)
//
//        val uid = uiState.uid
//        Text("UID: $uid")
        Text("Test")
    }
}
