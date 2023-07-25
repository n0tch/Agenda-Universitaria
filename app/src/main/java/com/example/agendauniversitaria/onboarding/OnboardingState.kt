package com.example.agendauniversitaria.onboarding

sealed class OnboardingState{
    object OnboardingIdle: OnboardingState()
    class OnboardingUserLogged(val isLogged: Boolean): OnboardingState()
    class OnboardingUserLoggedError(val exception: Exception): OnboardingState()
}
