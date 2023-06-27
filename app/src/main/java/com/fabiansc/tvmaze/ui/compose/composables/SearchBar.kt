package com.fabiansc.tvmaze.ui.compose.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.ui.compose.theme.Shapes
import com.fabiansc.tvmaze.ui.compose.theme.Typography

@Composable
fun SearchBar(
    backPressed: () -> Unit,
    onSearch: (String) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = FocusRequester()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { backPressed() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = "Button back"
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .border(width = 1.dp, color = Color.White, shape = Shapes.small),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                value = textState, onValueChange = { newValue ->
                    textState = newValue
                },
                textStyle = typography.body1.copy(color = Color.White),
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (textState.text.trim().isNotEmpty()) {
                            onSearch(textState.text)
                        }
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                cursorBrush = SolidColor(Color.White)
            )
        }

        AnimatedVisibility(visible = textState.text.isNotEmpty()) {
            IconButton(onClick = { textState = TextFieldValue("") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Close Icon"
                )
            }
        }
    }
}