package es.yeffry.cryptoappcompose.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(title: String) {
    Text(
        text = title,
        fontSize = 50.sp,
        fontStyle = FontStyle.Normal,
        color = Color.White,
        modifier = Modifier
            .padding(start = 0.dp, top = 24.dp, end = 0.dp, bottom = 0.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}