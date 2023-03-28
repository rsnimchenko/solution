package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.NoResultItem
import com.youarelaunched.challenge.ui.screen.view.components.TopBar
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()

    VendorsScreen(uiState = uiState)
}

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) },
        topBar = {
            if (!uiState.vendors.isNullOrEmpty())
                TopBar()
        }
    ) { paddings ->
        if (!uiState.vendors.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddings)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    vertical = 24.dp,
                    horizontal = 16.dp
                )
            ) {
                items(uiState.vendors) { vendor ->
                    VendorItem(
                        vendor = vendor
                    )
                }

            }
        } else {
            NoResultItem()
        }
    }
}