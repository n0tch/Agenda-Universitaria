package com.features.subject.list

import androidx.lifecycle.ViewModel
import com.core.common.Result
import com.core.domain.SubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(
    private val subjectUseCase: SubjectUseCase
) : ViewModel(), ContainerHost<SubjectState, SubjectListSideEffect> {

    override val container = container<SubjectState, SubjectListSideEffect>(SubjectState())

    fun fetchSubjects() = intent {
        reduce { state.copy(isLoading = true) }
        when(val subjectsResult = subjectUseCase.fetchSubjectsWithMvi()){
            is Result.Error -> postSideEffect(SubjectListSideEffect.Toast(subjectsResult.exception.toString()))
            is Result.Success -> reduce { state.copy(subjects = subjectsResult.data, isLoading = false) }
        }
    }

    fun setSideEffect(sideEffect: SubjectListSideEffect) = intent {
        postSideEffect(sideEffect)
    }

    fun searchSubject(query: String) = intent {
        when(val subjectsResult = subjectUseCase.searchSubjectByName(query)){
            is Result.Error -> postSideEffect(SubjectListSideEffect.Toast(subjectsResult.exception.toString()))
            is Result.Success -> reduce { state.copy(subjects = subjectsResult.data, isLoading = false) }
        }
    }
}