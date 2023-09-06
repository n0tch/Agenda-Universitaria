package com.feature.timetable.notification

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.alert.BasicAlertDialog
import com.core.designsystem.extensions.toMinuteAndSecond
import com.example.model.TimetableCompound
import java.time.Duration

@Composable
fun CreateNotificationDialog(
    timetable: TimetableCompound,
    onSaveNotification: (List<Long>) -> Unit = {},
    onDismiss: () -> Unit = {}
){
    var inTime by remember { mutableStateOf(false) }
    var fiveMinEarly by remember { mutableStateOf(false) }
    var tenMinEarly by remember { mutableStateOf(false) }

    val updateState: (Int) -> Unit = {
        when(it){
            0 -> { inTime = inTime.not() }
            1 -> { fiveMinEarly = fiveMinEarly.not() }
            2 -> { tenMinEarly = tenMinEarly.not() }
        }
    }

    val getState: (Int) -> Boolean = {
        when(it){
            0 -> inTime
            1 -> fiveMinEarly
            2 -> tenMinEarly
            else -> false
        }
    }

    val getTimes: () -> List<Long> = {
        val times = mutableListOf<Long>()
        if(inTime)
            times.add(timetable.timetable.startTime)
        if(fiveMinEarly)
            times.add(timetable.timetable.startTime - Duration.ofMinutes(5).toMillis())
        if(tenMinEarly)
            times.add(timetable.timetable.startTime - Duration.ofMinutes(10).toMillis())

        times
    }

    BasicAlertDialog(onDismiss = { onDismiss() }) {
        Card(Modifier.padding(12.dp)) {
            Text("Habilitar notificação de grade horaria para ${timetable.subject.name}?")
            Text("Selecione a frequencia")

            listOf("No horatio (${timetable.timetable.startTime.toMinuteAndSecond()})", "5 minutos antes", "10 munitos antes").forEachIndexed { index, text ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = getState(index), onCheckedChange = { updateState(index) })
                    Text(text)
                }
            }

            OutlinedButton(onClick = { onSaveNotification(getTimes()) }) {
                Text(text = "Salvar")
            }
        }
    }
}

@Preview
@Composable
fun NotificationDialogPreview() {
    CreateNotificationDialog(timetable = TimetableCompound())
}