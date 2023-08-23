package com.feature.exam.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ExamComponent(
    onBackClicked: () -> Unit,
    navigateToExam: (String?) -> Unit
) {
    val viewModel: ExamViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchExams()
    })

    ExamScreen(
        isLoading = uiState.loading,
        exams = uiState.exams,
        onFabClicked = { navigateToExam("meu id hereee") },
        onBackClicked = { onBackClicked() }
    )
}

@Preview
@Composable
fun ExamComponentPreview() {
    ExamComponent({},{})
}