package com.youarelaunched.challenge.ui.screen.view

import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.data.repository.model.Vendor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock


@OptIn(ExperimentalCoroutinesApi::class)
class VendorsVMTest {
    private val dispatcher = UnconfinedTestDispatcher()
    private val repository = mock<VendorsRepository>()

    private val vendors = listOf(
        Vendor(99, "Florists + Fashion", "", "", false, null, null),
        Vendor(8, "Pets", "", "", false, null, null),
        Vendor(1, "Cafe & Restaurant", "", "", false, null, null),
    )

    private val emptyCompanyName = ""
    private val correctCompanyName = "Flor"
    private val wrongCompanyName = "Wlor"

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
        Mockito.reset(repository)
    }

    @BeforeEach
    fun beforeEach() {
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `should return completed list`() = runBlocking {
        val vendorsVM = VendorsVM(repository)
        Mockito.`when`(repository.getVendors(emptyCompanyName))
            .thenReturn(vendors.filter { it.companyName.startsWith(emptyCompanyName, true) })

        vendorsVM.getVendors(emptyCompanyName)

        val expected = 3
        val actual = vendorsVM.uiState.value.vendors?.size

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should return empty list`() = runBlocking {
        val vendorsVM = VendorsVM(repository)
        Mockito.`when`(repository.getVendors(wrongCompanyName))
            .thenReturn(vendors.filter { it.companyName.startsWith(wrongCompanyName, true) })

        vendorsVM.getVendors(wrongCompanyName)

        val expected = 0
        val actual = vendorsVM.uiState.value.vendors?.size

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should return partly completed list`() = runBlocking {
        val vendorsVM = VendorsVM(repository)
        Mockito.`when`(repository.getVendors(correctCompanyName))
            .thenReturn(vendors.filter { it.companyName.startsWith(correctCompanyName, true) })

        vendorsVM.getVendors(correctCompanyName)

        val expected = 1
        val actual = vendorsVM.uiState.value.vendors?.size

        Assertions.assertEquals(expected, actual)
    }

}