package com.core.data.repository.exam

import app.cash.turbine.test
import com.core.database.exam.ExamDao
import com.example.model.event.Exam
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ExamRepositoryImpTest {

    private val examDaoMock = mockk<ExamDao>()
    private val examRepository = ExamRepositoryImp(examDaoMock)

    @Test
    fun `WHEN fetch exam it SHOULD return exam with correct values`() = runTest {
        val exam = Exam.getMock()

        coEvery { examDaoMock.saveExam(exam.toEntity()) } just Runs

        examRepository.saveExam(exam).test {
            val output = awaitItem()

            awaitComplete()

            assertEquals(exam.name, output.name)
            assertEquals(exam.subjectId, output.subjectId)
            assertEquals(exam.date, output.date)
            assertEquals(exam.score, output.score)
        }
    }

    @Test
    fun `WHEN exam dao throws an exception it SHOULD throw same exception`() = runTest {
        val exam = Exam.getMock()
        val exception = IOException()
        coEvery { examDaoMock.saveExam(exam.toEntity()) } throws exception

        examRepository.saveExam(exam).test {
            val item = awaitError()
            assertTrue(item is IOException)
        }
    }
}