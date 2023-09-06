package com.features.subject.detail

import com.example.model.SubjectCompound

data class SubjectDetailState(
    val isLoading: Boolean = false,
    val subjectCompound: SubjectCompound = SubjectCompound(null, emptyList(), emptyList()),
    val exception: Exception? = null
)