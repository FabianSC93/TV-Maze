package com.fabiansc.tvmaze.ui.compose.composables

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.data.DataResultError
import com.fabiansc.tvmaze.ui.compose.theme.Typography

@Composable
fun ErrorMessage(
    type: DataResultError
) {
    when (type) {
        DataResultError.NoInternet -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.txt_message_no_internet),
                    style = Typography.body2,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_no_intenet),
                    contentDescription = "No Connexion"
                )
            }

        }
        DataResultError.EmptyResult -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.txt_message_empty_result),
                    style = Typography.body2,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_empty_result),
                    contentDescription = "No Result"
                )
            }
        }
        else -> {

        }
    }
}