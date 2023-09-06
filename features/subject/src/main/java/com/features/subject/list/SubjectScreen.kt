package com.features.subject.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun SubjectComponent(
    onBackPressed: () -> Unit = {},
    navigateToSubjectDetail: (name: String, id: Int) -> Unit = {_,_ -> }
) {

    val viewModel: SubjectViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var openAddSubjectDialog by remember { mutableStateOf(false) }

    SubjectContent(
        subjects = uiState.subjects,
        isLoading = uiState.isLoading,
        onAddSubjectClicked = {
            openAddSubjectDialog = true
        },
        onSubjectDetailClicked = { subject ->
            navigateToSubjectDetail(subject.name, subject.id)
        },
        onBackClicked = onBackPressed
    )

    //TODO remove this from here
    if (openAddSubjectDialog) {
        SubjectAddDialog(
            onSaveButton = { subjectName ->
                viewModel.saveSubject(subjectName)
                openAddSubjectDialog = false
            }, onDismiss = {
                openAddSubjectDialog = false
            }
        )
    }
}

@Preview
@Composable
fun SubjectComponentPreview() {
    SubjectComponent()
}