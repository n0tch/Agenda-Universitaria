package com.core.designsystem.components.drawer

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable

@Composable
fun BasicDrawer(
    drawerState: DrawerState,
    drawerContent: @Composable () -> Unit,
    screenContent: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = { ModalDrawerSheet { drawerContent() } },
        content = { screenContent() })
}