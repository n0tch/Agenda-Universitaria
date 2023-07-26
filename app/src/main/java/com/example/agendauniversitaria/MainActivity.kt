package com.example.agendauniversitaria

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.agendauniversitaria.navigation.AgendaNavHost
import com.example.agendauniversitaria.onboarding.OnboardingState
import com.example.agendauniversitaria.onboarding.OnboardingViewModel
import com.example.agendauniversitaria.ui.theme.AgendaTheme
import com.feature.navigation.authentication.login.loginGraphRoute
import com.feature.navigation.home.homeGraphRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgendaTheme {
                // A surface container using the 'background' color from the theme

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

//                    val viewModel: OnboardingViewModel = hiltViewModel()
//                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//
//                    LaunchedEffect(key1 = Unit, block = {
//                        viewModel.isUserLoggedIn()
//                    })
//
//                    when (uiState) {
//                        OnboardingState.OnboardingIdle -> {}
//                        is OnboardingState.OnboardingUserLogged -> {
////                            LaunchedEffect(key1 = Unit, block = {
////
////                            })
//                            val startScreen =
//                                if ((uiState as OnboardingState.OnboardingUserLogged).isLogged) {
//                                    homeGraphRoute
//                                } else {
//                                    loginGraphRoute
//                                }
//                            AgendaNavHost(startScreen)
//                        }
//
//                        is OnboardingState.OnboardingUserLoggedError -> {}
//                    }

                    AgendaNavHost(homeGraphRoute)
                }
            }
        }
    }
}
