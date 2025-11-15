package com.example.baseproject.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseproject.ui.screens.buttons.ComponentSection
import com.example.baseproject.ui.theme.BaseProjectTheme
import com.example.baseproject.ui.theme.petSafe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen (
    onBackClick:() -> Unit,
    onRegisterClick: () -> Unit // 1. Nuevo parámetro para la acción de registro
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var password2Visible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = petSafe
                )
            )
        }
    ){paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ComponentSection(title = "Crea tu cuenta en Pet Society") {
                    val isEmailError = email.isNotEmpty() && (email.length < 5 || !email.contains("@"))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Ingresa tu correo") },
                        isError = isEmailError,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        supportingText = { if (isEmailError) Text("Ingresa un correo válido") },
                        trailingIcon = { if (isEmailError) Icon(Icons.Default.Error, "Error", tint = MaterialTheme.colorScheme.error) }
                    )

                    val isPasswordError = password.isNotEmpty() && password.length < 6

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Ingresa tu contraseña") },
                        isError = isPasswordError,
                        singleLine = true,
                        supportingText = { if (isPasswordError) Text("Debe tener al menos 6 caracteres") },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = {passwordVisible = !passwordVisible}){
                                Icon(imageVector  = image, contentDescription = if (passwordVisible) "Ocultar" else "Mostrar")
                            }
                        }
                    )

                    val passwordsMismatchError = password2.isNotEmpty() && password != password2

                    OutlinedTextField(
                        value = password2,
                        onValueChange = { password2 = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Repite tu contraseña") },
                        isError = passwordsMismatchError,
                        singleLine = true,
                        supportingText = { if (passwordsMismatchError) Text("Las contraseñas no coinciden") },
                        visualTransformation = if (password2Visible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            val image = if (password2Visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                            IconButton(onClick = {password2Visible = !password2Visible}){
                                Icon(imageVector  = image, contentDescription = if (password2Visible) "Ocultar" else "Mostrar")
                            }
                        }
                    )

                    // 2. Lógica de validación antes de permitir el click
                    val isFormValid = email.isNotEmpty() && !isEmailError &&
                                      password.isNotEmpty() && !isPasswordError &&
                                      password2.isNotEmpty() && !passwordsMismatchError

                    Button(
                        onClick = onRegisterClick, // 3. Llama a la nueva acción
                        enabled = isFormValid, // El botón solo se activa si el formulario es válido
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Registrarse")
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    BaseProjectTheme {
        LoginScreen(
            onBackClick = {},
            onRegisterClick = {} // 4. Actualizamos el preview
        )
    }
}