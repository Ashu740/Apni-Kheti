package com.example.apnikheti.View.Topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(content: @Composable () -> Unit, scrollBehavior: TopAppBarScrollBehavior){

    val topAppBarElementColor = MaterialTheme.colorScheme.onSurface
    LargeTopAppBar(
        title = content,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            scrolledContainerColor = MaterialTheme.colorScheme.surface,
            navigationIconContentColor = topAppBarElementColor,
            titleContentColor = topAppBarElementColor,
            actionIconContentColor = topAppBarElementColor,
        ),
        scrollBehavior = scrollBehavior
    )
}