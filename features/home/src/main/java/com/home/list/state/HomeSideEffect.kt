package com.home.list.state

import java.time.DayOfWeek

sealed class HomeSideEffect{
    data class Toast(val message: String): HomeSideEffect()
    data class DaySelected(val dayOfWeek: DayOfWeek): HomeSideEffect()
}