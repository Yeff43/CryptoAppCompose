package es.yeffry.cryptoappcompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End,
    ) {
        Text(
            text = "By Yeffry Rodriguez.", color = Color.White,
            modifier = Modifier.padding(start = 0.dp, top = 0.dp, end = 16.dp, bottom = 4.dp)
        )
    }
}