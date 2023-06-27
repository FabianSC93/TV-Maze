package com.fabiansc.tvmaze.ui.compose.composables

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.core.getCompleteDate
import java.util.*

@Composable
fun MainTopBar(
    onSearch: (String) -> Unit,
    backPressed: () -> Unit
) {
    var showTextField by remember { mutableStateOf(false) }

    TopAppBar {
        if (showTextField) {
            SearchBar(
                backPressed = {
                    showTextField = false
                    backPressed()
                },
                onSearch = { query ->
                    onSearch(query)
                })
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = Date().getCompleteDate()
                )
                IconButton(onClick = { showTextField = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Icon Search"
                    )
                }
            }
        }
    }
}