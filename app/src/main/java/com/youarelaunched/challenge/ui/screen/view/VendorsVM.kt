package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        VendorsScreenUiState(
            vendors = null,
            searchFieldText = ""
        )
    )
    val uiState = _uiState.asStateFlow()

    private var searchJob: Job? = null

    init {
        getVendors("")
    }

    private fun getVendors(companyName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    vendors = repository.getVendors(companyName)
                )
            }
        }
    }

    fun updateSearchFieldText(text: String) {
        _uiState.update {
            it.copy(
                searchFieldText = text
            )
        }
    }

    fun startSearch() {
        searchJob?.let { if (it.isActive) it.cancel() }
        searchJob = viewModelScope.launch {
            delay(500L)
            getVendors(_uiState.value.searchFieldText.trimEnd())
        }
    }

}