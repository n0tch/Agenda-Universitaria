package com.features.authentication.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QueryBuilder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core.designsystem.components.LoadingView
import com.core.designsystem.ui.LightBlue
import com.core.designsystem.ui.White
import com.example.model.LoginSocialOption
import com.features.authentication.R

private const val HEADER_HEIGHT = 0.35f

@Composable
fun LoginScreen(
    isLoading: Boolean = false,
    onForgotPass: () -> Unit,
    onLogin: (email: String, password: String) -> Unit,
    onLoginWithSocial: (LoginSocialOption) -> Unit = {},
    onRegisterClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LoginHeader()
        LoginMainContent {
            LoginForm(onLogin = onLogin, onRegisterClicked = onRegisterClicked)
            LoginSocial(onLoginWithSocial = onLoginWithSocial)
            ForgotPass(onForgotPass = onForgotPass)
        }
    }

    if(isLoading){
        LoadingView()
    }
}

@Composable
fun LoginMainContent(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(
            topStart = 50f,
            topEnd = 50f,
            bottomEnd = 0f,
            bottomStart = 0f
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Composable
fun LoginHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(HEADER_HEIGHT),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.baseline_book),
            contentDescription = "Logo"
        )
        Text(
            text = "Agenda",
            color = MaterialTheme.colorScheme.onSecondary,
            fontSize = MaterialTheme.typography.displaySmall.fontSize
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    onLogin: (email: String, password: String) -> Unit,
    onRegisterClicked: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var passVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            value = email,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = { email = it },
            placeholder = { Text("Email") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pass,
            onValueChange = { pass = it },
            singleLine = true,
            placeholder = { Text("Password") },
            visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (passVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                IconButton(onClick = { passVisible = !passVisible }) {
                    Icon(imageVector = image, contentDescription = "password toggle")
                }
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = { onRegisterClicked() }) {
                Text(text = "Cadastrar")
            }
        }

        OutlinedButton(onClick = { onLogin(email, pass) }) {
            Text(modifier = Modifier.padding(horizontal = 18.dp), text = "Login")
        }
    }
}

@Composable
fun LoginSocial(onLoginWithSocial: (LoginSocialOption) -> Unit) {
    Column {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Or Login with",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { onLoginWithSocial(LoginSocialOption.GOOGLE) }
            ) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "")
            }
            OutlinedButton(
                modifier = Modifier.padding(horizontal = 12.dp),
                onClick = { onLoginWithSocial(LoginSocialOption.FACEBOOK) }) {
                Icon(imageVector = Icons.Filled.Train, contentDescription = "")
            }
            OutlinedButton(onClick = { onLoginWithSocial(LoginSocialOption.ANONYMOUS) }) {
                Icon(imageVector = Icons.Filled.QueryBuilder, contentDescription = "")
            }
        }
    }
}

@Composable
fun ForgotPass(onForgotPass: () -> Unit) {
    TextButton(
        onClick = { onForgotPass() }
    ){
        Text(text = "Esqueci minha senha")
    }
}

@Preview
@Composable
fun LoginScreenLoadingPreview() {
    LoginScreen(isLoading = true, onForgotPass = {}, onLogin = { _, _ -> })
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(isLoading = false, onForgotPass = {}, onLogin = { _, _ -> })
}