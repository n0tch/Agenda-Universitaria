package com.features.subject.edit

import androidx.lifecycle.ViewModel
import com.core.common.Result
import com.core.domain.SubjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SubjectEditViewModel @Inject constructor(
    private val subjectUseCase: SubjectUseCase
) : ViewModel(), ContainerHost<SubjectEditState, SubjectEditSideEffect> {

    override val container = container<SubjectEditState, SubjectEditSideEffect>(SubjectEditState())

    fun saveSubject(subject: SubjectEditAction.SaveSubject) = intent {
        val savedResult = subjectUseCase.saveSubject(
            subject = subject.subject,
            timetable = subject.timetable,
            notificationEnabled = subject.notificationEnabled,
            notificationEarlier = subject.notifyEarlier
        )
        when(savedResult){
            is Result.Error -> postSideEffect(SubjectEditSideEffect.Toast(savedResult.exception.message.toString()))
            is Result.Success -> postSideEffect(SubjectEditSideEffect.SubjectSaved)
        }
    }
}