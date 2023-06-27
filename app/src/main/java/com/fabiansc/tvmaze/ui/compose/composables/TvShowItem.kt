package com.fabiansc.tvmaze.ui.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fabiansc.tvmaze.domain.model.TvShowModel

@Composable
fun TvShowItem(
    tvShow: TvShowModel,
    clickItem: (String) -> Unit
) {
    Card(
        modifier = Modifier.clickable { clickItem(tvShow.id.toString()) },
        elevation = 5.dp
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = rememberImagePainter(data = tvShow.image ?: ""),
                contentDescription = "TV Show Poster"
            )
            Column {
                Text(text = tvShow.name)
                Text(text = tvShow.network)
                Text(text = tvShow.dates)
            }
        }
    }
}