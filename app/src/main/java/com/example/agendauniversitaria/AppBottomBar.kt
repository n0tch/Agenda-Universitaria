package com.example.agendauniversitaria

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.core.designsystem.components.CustomBottomBar
import com.feature.navigation.BottomBarScreens
import com.feature.navigation.exam.navigateToExams
import com.feature.navigation.home.navigateToHome
import com.feature.navigation.note.navigateToNotes
import com.feature.navigation.subject.navigateToSubjects

@Composable
fun AppBottomBar(navController: NavHostController) {

    var selectedScreen by remember { mutableStateOf(BottomBarScreens.HOME) }
    val isHome = navController.currentBackStackEntryAsState().value?.destination?.route == "home_route"

    CustomBottomBar(
        items = BottomBarScreens.values().toList(),
        selected = selectedScreen,
        content = { UnselectedBottomBarMenu(item = it) },
        selectedContent = { SelectedBottomBarMenu(item = it) },
        onItemSelected = {
            selectedScreen = it
            when (it) {
                BottomBarScreens.HOME -> navController.navigateToHome()
                BottomBarScreens.CALENDAR -> navController.navigateToSubjects()
                BottomBarScreens.NOTE -> navController.navigateToNotes()
                BottomBarScreens.PROFILE -> navController.navigateToExams()
            }
        }
    )

    if(isHome){
        selectedScreen = BottomBarScreens.HOME
    }
}

@Composable
fun UnselectedBottomBarMenu(item: BottomBarScreens) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = "",
            tint = Color.Black
        )
        Text(item.label, fontSize = 8.sp, color = Color.Black)
    }
}

@Composable
fun SelectedBottomBarMenu(item: BottomBarScreens) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = "",
            tint = Color.White
        )
        Text(item.label, fontSize = 8.sp, color = Color.White)
    }
}
