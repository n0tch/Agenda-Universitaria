package com.features.subject.list

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun SubjectComponent(
    onBackPressed: () -> Unit = {},
    navigateToSubjectDetail: (name: String, id: Int) -> Unit = { _, _ -> },
    navigateToSubjectEdit: () -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel: SubjectViewModel = hiltViewModel()
    val state by viewModel.collectAsState()

    LaunchedEffect(Unit){
        viewModel.fetchSubjects()
    }

    SubjectContent(
        state = state,
        onAction = viewModel::setSideEffect,
        onSearch = viewModel::searchSubject,
    )

    viewModel.collectSideEffect {
        when (it) {
            is SubjectListSideEffect.Toast -> Toast.makeText(
                context,
                it.message,
                Toast.LENGTH_SHORT
            ).show()

            SubjectListSideEffect.NavigateToNewSubject -> navigateToSubjectEdit()
            is SubjectListSideEffect.NavigateToDetail -> navigateToSubjectDetail(it.subject.name, it.subject.id)
            is SubjectListSideEffect.OnBack -> onBackPressed()
        }
    }
}

@Preview
@Composable
fun SubjectComponentPreview() {
    SubjectComponent()
}