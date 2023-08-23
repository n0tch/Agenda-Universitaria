package com.features.subject.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.card.CardForward
import com.example.model.Subject

@Composable
fun SubjectListComponent(
    modifier: Modifier = Modifier,
    subjects: List<Subject> = emptyList(),
    onCardClicked: (Subject) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize(), content = {
        items(subjects) { subject ->
            CardForward(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 6.dp),
                title = subject.name,
                body = "",
                onClick = {
                    onCardClicked(subject)
                }
            )
        }
    })
}

@Preview
@Composable
fun SubjectListComponentPreview() {
    SubjectListComponent(){}
}