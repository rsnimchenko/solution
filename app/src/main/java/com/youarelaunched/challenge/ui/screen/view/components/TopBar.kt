package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@Composable
fun TopBar(
    searchFieldText: String,
    updateText: (String) -> Unit,
    startSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(VendorAppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        SearchField(
            searchFieldText = searchFieldText,
            updateText = updateText,
            startSearch = startSearch
        )
    }
}

@Composable
fun SearchField(
    searchFieldText: String,
    updateText: (String) -> Unit,
    startSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = searchFieldText,
        onValueChange = {
            updateText(it.trimStart())
            if (it.isEmpty() || it.length >= 3)
                startSearch()
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .shadow(
                elevation = 5.dp,
                shape = VendorAppTheme.shapes.medium,
                ambientColor = Color(0x03000000)
            ),
        textStyle = VendorAppTheme.typography.subtitle2,
        colors = TextFieldDefaults.textFieldColors(
            textColor = VendorAppTheme.colors.text,
            cursorColor = VendorAppTheme.colors.text,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = VendorAppTheme.shapes.medium,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_placeholder),
                style = VendorAppTheme.typography.subtitle2,
                color = VendorAppTheme.colors.text
            )
        },
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.icon_search),
                "search_icon",
                tint = VendorAppTheme.colors.text,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { startSearch() }
                )
            )
        }
    )
}