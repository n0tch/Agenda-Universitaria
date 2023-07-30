package com.features.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.AppDispatcher
import com.core.common.Dispatcher
import com.core.common.Result
import com.core.domain.SubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    @Dispatcher(AppDispatcher.UI) private val uiDispatcher: CoroutineDispatcher,
    private val subjectUseCase: SubjectUseCase
) : ViewModel() {

    init {
        fetchSubjects()
    }

    val uiState: MutableStateFlow<SubjectState> by lazy { MutableStateFlow(SubjectState.Idle) }

    fun saveSubject(name: String) {
        viewModelScope.launch {
            subjectUseCase
                .saveSubject(name)
                .flowOn(uiDispatcher)
                .onStart { uiState.emit(SubjectState.Loading) }
                .catch { uiState.emit(SubjectState.Error(it as Exception)) }
                .collect {
                    when (it) {
                        is Result.Error -> uiState.emit(SubjectState.Error(it.exception))
                        is Result.Success -> uiState.emit(SubjectState.SavedSuccess(it.data))
                    }
                }
        }
    }

    fun fetchSubjects(){
        viewModelScope.launch {
            subjectUseCase
                .getSubjects()
                .flowOn(uiDispatcher)
                .catch { uiState.emit(SubjectState.Error(it as Exception)) }
                .collect {
                    when(it){
                        is Result.Error -> uiState.emit(SubjectState.Error(it.exception))
                        is Result.Success -> uiState.emit(SubjectState.FetchSuccess(it.data))
                    }
                }
        }
    }
}