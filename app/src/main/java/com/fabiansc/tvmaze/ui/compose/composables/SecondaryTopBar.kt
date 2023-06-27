package com.fabiansc.tvmaze.ui.compose.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabiansc.tvmaze.R

@Composable
fun SecondaryTopBar(
    backPressed: () -> Unit
) {
    TopAppBar {
        IconButton(onClick = { backPressed() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                tint = Color.Unspecified,
                contentDescription = "Button back"
            )

        }
        Text(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp),
            text = stringResource(R.string.txt_label_episodes),
            color = Color.White,
            fontSize = 20.sp
        )

    }
}