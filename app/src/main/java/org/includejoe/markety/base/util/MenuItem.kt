package org.includejoe.markety.base.util

import androidx.compose.ui.graphics.vector.ImageVector

class MenuItem(
    val icon: ImageVector,
    val label: Int,
    val onClick: () -> Unit
)
