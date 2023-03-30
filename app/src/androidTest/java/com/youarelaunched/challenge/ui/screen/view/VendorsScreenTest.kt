package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import org.junit.Rule
import org.junit.Test

class VendorsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val vendors = listOf(
        Vendor(99, "Florists + Fashion", "", "", false, null, null),
        Vendor(8, "Pets", "", "", false, null, null),
        Vendor(1, "Cafe & Restaurant", "", "", false, null, null),
    )

    @Test
    fun testFullListIsDisplayed() {
        val vendorsScreenUiState = VendorsScreenUiState(
            vendors = vendors,
            searchFieldText = ""
        )

        composeTestRule.setContent {
            VendorAppTheme {
                VendorsScreen(uiState = vendorsScreenUiState, {}, {})
            }
        }

        composeTestRule.onNodeWithText("Florists + Fashion").assertIsDisplayed()
        composeTestRule.onNodeWithText("Pets").assertIsDisplayed()
        composeTestRule.onNodeWithText("Cafe & Restaurant").assertIsDisplayed()
    }

    @Test
    fun testListIsEmptyDisplayed() {
        val vendorsScreenUiState = VendorsScreenUiState(
            vendors = listOf(),
            searchFieldText = ""
        )

        composeTestRule.setContent {
            VendorAppTheme {
                VendorsScreen(uiState = vendorsScreenUiState, {}, {})
            }
        }

        composeTestRule.onNodeWithText("Sorry! No results found…").assertIsDisplayed()
        composeTestRule.onNodeWithText("Please try a different search request or browse businesses from the list")
            .assertIsDisplayed()
    }

    @Test
    fun testPartlyListIsDisplayed() {
        val vendorsScreenUiState = VendorsScreenUiState(
            vendors = vendors,
            searchFieldText = "Flor"
        )

        composeTestRule.setContent {
            VendorAppTheme {
                VendorsScreen(uiState = vendorsScreenUiState, {}, {})
            }
        }

        composeTestRule.onNodeWithContentDescription("search_icon").performClick()
        composeTestRule.onNodeWithText("Florists + Fashion").assertIsDisplayed()
    }
}