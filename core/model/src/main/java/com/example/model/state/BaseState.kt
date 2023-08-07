package com.example.model.state

interface BaseState {
    val loading: Boolean
    val error: Exception?
}