package com.example.baseproject.ui.screens.reviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseproject.ui.theme.BaseProjectTheme
import com.example.baseproject.ui.theme.petSafe

// Opciones de doctores
val mockDoctors = listOf("Dr. Smith", "Dr. Johnson", "Dr. Álvarez")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(
    onSubmitReview: (
        petName: String,
        ownerName: String,
        doctor: String,
        rating: Int,
        comment: String
    ) -> Unit,
    onCancel: () -> Unit,
    onLogoutClick: () -> Unit,
    onOpenDrawer: () -> Unit,
    onSelectFile: () -> Unit
) {
    // 1.- Manejo del Estado
    var petName by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    var selectedDoctor by remember { mutableStateOf(mockDoctors.firstOrNull() ?: "Selecciona un doctor") }

    val isFormValid = petName.isNotEmpty() && ownerName.isNotEmpty() && rating > 0 && comment.isNotEmpty()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Subir Reseña", color = Color.White) }, // 💡 Título Corregido
                navigationIcon = {
                    IconButton(onClick = onOpenDrawer) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Abrir menú lateral",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    TextButton(onClick = onLogoutClick) {
                        Text("Cerrar sesión", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = petSafe
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Formulario de Reseña",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // 2.- Sección de Subida de Imagen
                        Text(
                            "Imagen de tu Mascota",
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.align(Alignment.Start)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Ningún archivo seleccionado",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f).padding(end = 8.dp)
                            )

                            // Llama a la acción que abre la galería
                            Button(onClick = onSelectFile) {
                                Text("Seleccionar archivo")
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // 3.- Campos de Mascota y Dueño (sin cambios)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            OutlinedTextField(
                                value = petName,
                                onValueChange = { petName = it },
                                label = { Text("Nombre de la Mascota") },
                                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                                modifier = Modifier.weight(1f)
                            )
                            OutlinedTextField(
                                value = petBreed,
                                onValueChange = { petBreed = it },
                                label = { Text("Raza de la Mascota") },
                                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                                modifier = Modifier.weight(1f)
                            )
                        }

                        OutlinedTextField(
                            value = ownerName,
                            onValueChange = { ownerName = it },
                            label = { Text("Nombre del Dueño") },
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 4.- Doctor (Dropdown corregido)
                        DoctorDropdown(
                            doctors = mockDoctors,
                            selectedDoctor = selectedDoctor,
                            onDoctorSelected = { selectedDoctor = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // 5.- Calificación (sin cambios)
                        Text("Tu Calificación", style = MaterialTheme.typography.bodyLarge)
                        RatingSelector(rating = rating) { newRating -> rating = newRating }

                        Spacer(modifier = Modifier.height(16.dp))

                        // 6.- Comentarios (sin cambios)
                        OutlinedTextField(
                            value = comment,
                            onValueChange = { comment = it },
                            label = { Text("Comentarios") },
                            minLines = 4,
                            maxLines = 8,
                            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // 7.- Botones de Acción
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    onSubmitReview(petName, ownerName, selectedDoctor, rating, comment)
                                },
                                enabled = isFormValid, // Usa la validación
                                colors = ButtonDefaults.buttonColors(containerColor = petSafe),
                                modifier = Modifier.weight(1f).padding(end = 8.dp)
                            ) {
                                Text("Subir Reseña")
                            }
                            OutlinedButton(
                                onClick = onCancel,
                                modifier = Modifier.weight(1f).padding(start = 8.dp)
                            ) {
                                Text("Cancelar")
                            }
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorDropdown(doctors: List<String>, selectedDoctor: String, onDoctorSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    // 💡 SOLUCIÓN: USAR ExposedDropdownMenuBox PARA CLICKS CONFIABLES
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        // TextField debe usar .menuAnchor()
        OutlinedTextField(
            value = selectedDoctor,
            onValueChange = {},
            label = { Text("Doctor que lo atendió") },
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            doctors.forEach { doctor ->
                DropdownMenuItem(
                    text = { Text(doctor) },
                    onClick = {
                        onDoctorSelected(doctor)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun RatingSelector(rating: Int, onRatingChange: (Int) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        repeat(5) { index ->
            val starValue = index + 1
            val isFilled = starValue <= rating
            IconButton(onClick = { onRatingChange(starValue) }) {
                Icon(
                    imageVector = if (isFilled) Icons.Filled.Star else Icons.Filled.StarBorder,
                    contentDescription = "Star $starValue",
                    tint = if (isFilled) Color(0xFFFFC107) else Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddReviewScreenPreview() {
    BaseProjectTheme {
        AddReviewScreen(
            onSubmitReview = { _, _, _, _, _ -> },
            onCancel = {},
            onLogoutClick = {},
            onOpenDrawer = {},
            onSelectFile = {}
        )
    }
}