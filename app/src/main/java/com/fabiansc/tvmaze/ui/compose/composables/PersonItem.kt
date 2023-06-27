package com.fabiansc.tvmaze.ui.compose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.fabiansc.tvmaze.domain.model.PersonModel

@Composable
fun PersonItem(
    person: PersonModel,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            modifier = Modifier.size(height = 120.dp, width = 80.dp),
            painter = rememberImagePainter(data = person.image),
            contentScale = ContentScale.Crop,
            contentDescription = "Person's Photo"
        )
        Text(
            text = person.name,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 12.sp,
            color = if(isSystemInDarkTheme()) Color.White else Color.Black
        )
    }
}