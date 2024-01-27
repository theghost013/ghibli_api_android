package com.example.ghibliapi.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ghibliapi.ui.screen.GhibliFilm
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp

@Composable
fun FilmRowItem(modifier: Modifier = Modifier, data: GhibliFilm, onClick : () -> Unit = {}) {

    Row(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .heightIn(max = 100.dp)
        .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
    ) {

        Spacer(modifier = Modifier.size(8.dp))

        Column(modifier = Modifier
            .clickable(onClick = onClick)
        ) {
            Text(
                text = data.title,
                fontSize = 20.sp
            )
            Text(
                text = data.director,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.animateContentSize()
            )
        }
    }
}