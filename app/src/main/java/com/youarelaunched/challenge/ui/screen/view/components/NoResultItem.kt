package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun NoResultItem(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(VendorAppTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.empty_vendor_title),
            style = VendorAppTheme.typography.h2,
            color = VendorAppTheme.colors.textAlertTitle,
            modifier = Modifier.padding(horizontal = 44.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.empty_vendor_message),
            style = VendorAppTheme.typography.subtitle2,
            color = VendorAppTheme.colors.textDark,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 27.dp)

        )
    }
}