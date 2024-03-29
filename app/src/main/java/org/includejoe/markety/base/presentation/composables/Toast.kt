package org.includejoe.markety.base.presentation.composables

import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun Toast(message: String) {
    makeText(
        LocalContext.current,
        message,
        LENGTH_SHORT
    ).show()
}