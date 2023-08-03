package com.features.subject.exam

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ExamComponent() {
    val viewModel: ExamViewModel = hiltViewModel()

    ExamScreen()
}

@Preview
@Composable
fun ExamComponentPreview() {
    ExamComponent()
}