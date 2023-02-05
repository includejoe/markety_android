package org.includejoe.markety.feature_user.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TabView(
    modifier: Modifier = Modifier,
    tabModels: List<Int>,
    onTabSelected: (selectedTabIndex: Int) -> Unit,
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onBackground,
        indicator = {
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(it[selectedTabIndex]),
                color = MaterialTheme.colors.primary,
                height = TabRowDefaults.IndicatorHeight * 1.5f
            )
        },
        modifier = modifier.fillMaxWidth()
    ) {
        tabModels.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                },
                selectedContentColor = MaterialTheme.colors.onBackground,
            ) {
                Text(
                    text = stringResource(id = item),
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
    }
}