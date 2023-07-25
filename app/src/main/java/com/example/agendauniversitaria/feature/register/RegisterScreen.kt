package com.example.agendauniversitaria.feature.register

import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.agendauniversitaria.designsystem.LoadingView
import com.example.agendauniversitaria.designsystem.photopicker.photoPicker
import com.example.agendauniversitaria.ui.theme.AgendaTheme
import com.example.agendauniversitaria.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    isLoading: Boolean,
    onRegisterButtonClicked: (String, String, String, Uri?) -> Unit,
    onBackButtonClicked: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var profileUri: Uri? by remember { mutableStateOf(null) }

    val photoPicker = photoPicker(onPhotoPicked = {
        profileUri = it
    })

    Surface(modifier = Modifier.fillMaxSize(), color = LightBlue) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(30.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            AsyncImage(
                modifier = Modifier
                    .size(150.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    },
                model = profileUri,
                contentScale = ContentScale.Crop,
                contentDescription = "choose pick"
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = email, onValueChange = { email = it }, placeholder = {
                Text(
                    text = "email"
                )
            })
            OutlinedTextField(value = password, onValueChange = { password = it }, placeholder = {
                Text(
                    text = "Senha"
                )
            })
            OutlinedTextField(value = userName, onValueChange = { userName = it }, placeholder = {
                Text(
                    text = "Nome de usuário"
                )
            })

            OutlinedButton(onClick = { onRegisterButtonClicked(email, password, userName, profileUri) }) {
                Text(text = "Registrar")
            }

            TextButton(onClick = { onBackButtonClicked() }) {
                Text(text = "Voltar")
            }
        }
    }

    if(isLoading){
        LoadingView()
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    AgendaTheme {
        RegisterScreen(false, { _, _, _, _ -> }, {})
    }
}

@Preview
@Composable
fun RegisterScreenLoadingPreview() {
    AgendaTheme {
        RegisterScreen(true, { _, _, _, _ -> }, {})
    }
}