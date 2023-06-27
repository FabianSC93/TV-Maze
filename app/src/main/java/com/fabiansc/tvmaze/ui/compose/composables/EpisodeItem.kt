package com.fabiansc.tvmaze.ui.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.RoundedCornersTransformation
import com.fabiansc.tvmaze.R
import com.fabiansc.tvmaze.domain.model.EpisodeModel

@Composable
fun EpisodeItem(
    episode: EpisodeModel
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(height = 100.dp, width = 50.dp),
            painter = rememberImagePainter(
                data = episode.image,
                builder = {
                    transformations(
                        RoundedCornersTransformation(
                            8f,
                            8f,
                            8f,
                            8f
                        )
                    )
                }),
            contentScale = ContentScale.Crop,
            contentDescription = "Episode's Photo"
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = R.string.txt_label_episode, episode.number, episode.name),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}