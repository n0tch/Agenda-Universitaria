package com.features.subject.exam.detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.model.Subject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamDetailComponent(
    examId: String,
    onBackPressed: () -> Unit = {},
    navigateToNotesWithResult: () -> Unit = {},
    notesSelectionLiveDat: MutableLiveData<List<String>>?
) {

    val viewModel: ExamDetailViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val examState by viewModel.examState.collectAsStateWithLifecycle().apply { value.id = examId }

    var relatedNotes: List<String> = remember { mutableStateListOf() }

    if (notesSelectionLiveDat?.value?.isNotEmpty() == true) {
        relatedNotes = notesSelectionLiveDat.value ?: emptyList()
    }

    when {
        uiState.error != null -> {}
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Provas") },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "back button"
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            ExamDetailScreen(
                isLoading = uiState.loading,
                subjects = uiState.subjects,//subjects,
                relatedNotes = relatedNotes,
                onAddNotes = { navigateToNotesWithResult() },
                examState = examState,
                onSaveExam = { viewModel.saveExam() }
            )
        }
    }
}

@Preview
@Composable
fun ExamDetailComponentPreview() {
    ExamDetailComponent("123", {}, notesSelectionLiveDat= MutableLiveData())
}