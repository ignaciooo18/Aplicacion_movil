package com.example.baseproject.ui.screens.veterinariodet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseproject.R
import com.example.baseproject.ui.screens.veterinarios.veterinariosDeEjemplo
import com.example.baseproject.ui.theme.BaseProjectTheme
import com.example.baseproject.ui.theme.petSafe
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinarioDetalleScreen(vetId: Int, onBackClick: () -> Unit) {
    val vet = veterinariosDeEjemplo.find { it.id == vetId }

    var fecha by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var mostrarDialogo by remember { mutableStateOf(false) }
    var mensajeDialogo by remember { mutableStateOf("") }

    val handleSubmitCita = fun() {
        if (fecha.isBlank() || hora.isBlank()) {
            mensajeDialogo = "Por favor, rellene el día y la hora de la cita."
            mostrarDialogo = true
            return
        }

        try {
            val formatoFecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            formatoFecha.isLenient = false
            val selectedDate = formatoFecha.parse(fecha)
            val hoy = Calendar.getInstance().apply { time = Date(); set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0) }.time

            val calendario = Calendar.getInstance().apply { time = selectedDate!! }
            if (calendario.get(Calendar.YEAR) != 2025) {
                mensajeDialogo = "El año de la cita debe ser obligatoriamente 2025."
                mostrarDialogo = true
                return
            }

            if (selectedDate.before(hoy) || selectedDate == hoy) {
                mensajeDialogo = "Por favor, selecciona una fecha futura."
                mostrarDialogo = true
                return
            }

            val (hours, _) = hora.split(":").map { it.toInt() }
            if (hours < 7 || hours >= 20) {
                mensajeDialogo = "El horario de atención es de 7:00 a 20:00."
                mostrarDialogo = true
                return
            }

            // TODO: Lógica de localStorage adaptada (p. ej. con SharedPreferences)

            mensajeDialogo = "Cita confirmada para el $fecha a las $hora."
            mostrarDialogo = true
            fecha = ""
            hora = ""
        } catch (e: Exception) {
            mensajeDialogo = "El formato de la fecha o la hora no es válido."
            mostrarDialogo = true
        }
    }

    if (vet == null) {
        Text("Veterinario no encontrado.")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(vet.nombre, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = petSafe)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFF0F0F0))
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // --- INICIO DE LA CORRECCIÓN ---
            // Se añade el Composable 'Image' al principio de la columna.
            Image(
                painter = painterResource(id = vet.imagenResId),
                contentDescription = "Foto de ${vet.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop // Esto asegura que la imagen llene el espacio sin deformarse
            )
            // --- FIN DE LA CORRECCIÓN ---

            // El resto de la UI continúa debajo
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(vet.nombre, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                Text(vet.especialidad, style = MaterialTheme.typography.titleMedium, color = Color.Gray)
                Text(vet.descripcion, style = MaterialTheme.typography.bodyLarge)
            }

            Divider(modifier = Modifier.padding(horizontal = 16.dp))

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Agendar una Cita", style = MaterialTheme.typography.titleLarge)
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Fecha (YYYY-MM-DD)") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = hora,
                    onValueChange = { hora = it },
                    label = { Text("Hora (HH:MM)") },
                    modifier = Modifier.fillMaxWidth()
                )
                Button(
                    onClick = handleSubmitCita,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Confirmar Cita")
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espacio final
        }

        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text("Aviso") },
                text = { Text(mensajeDialogo) },
                confirmButton = {
                    Button(onClick = { mostrarDialogo = false }) {
                        Text("Aceptar")
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun VeterinarioDetalleScreenPreview() {
    BaseProjectTheme {
        VeterinarioDetalleScreen(
            vetId = 1,
            onBackClick = {}
        )
    }
}
