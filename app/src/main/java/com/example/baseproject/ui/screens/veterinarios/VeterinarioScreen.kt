package com.example.baseproject.ui.screens.veterinarios

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseproject.R
import com.example.baseproject.ui.theme.BaseProjectTheme
import com.example.baseproject.ui.theme.petSafe

data class Veterinario(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val descripcion: String,
    val calificacion: Float,
    val totalResenas: Int,
    val imagenResId: Int
)

val veterinariosDeEjemplo = listOf(
    Veterinario(
        id = 1,
        nombre = "Dr. Juan Pérez",
        especialidad = "Cardiología y Cirugía",
        descripcion = "Especialista en enfermedades cardíacas y procedimientos quirúrgicos complejos. Con más de 15 años de experiencia.",
        calificacion = 4.0f,
        totalResenas = 120,
        imagenResId = R.drawable.veterinario
    ),
    Veterinario(
        id = 2,
        nombre = "Dr. Franco Maturaga",
        especialidad = "Dermatología y Nutrición",
        descripcion = "Experto en el cuidado de la piel y problemas de alergias. Ofrece planes de dieta personalizados para mascotas.",
        calificacion = 5.0f,
        totalResenas = 250,
        imagenResId = R.drawable.veterinario_5to
    ),
    Veterinario(
        id = 3,
        nombre = "Dr. Carlos Varela",
        especialidad = "Medicina General y Felinos",
        descripcion = "Conocido por su trato amable y su paciencia con los gatos. Ofrece chequeos generales y vacunas de rutina.",
        calificacion = 4.7f,
        totalResenas = 80,
        imagenResId = R.drawable.veterinario_carlos
    ),
    Veterinario(
        id = 4,
        nombre = "Dra. Laura Ramirez",
        especialidad = "Odontología y Oftalmología",
        descripcion = "Se especializa en el cuidado dental y ocular. Realiza limpiezas dentales y cirugías de cataratas.",
        calificacion = 4.7f,
        totalResenas = 15,
        imagenResId = R.drawable.veterinaria_valeria
    ),
    Veterinario(
        id = 5,
        nombre = "Dra. Juana Roman",
        especialidad = "Exóticos y Aves",
        descripcion = "Única especialista en reptiles, aves y pequeños mamíferos. Su clínica cuenta con instalaciones especializadas.",
        calificacion = 4.8f,
        totalResenas = 190,
        imagenResId = R.drawable.enfermera_mariana
    ),
    Veterinario(
        id = 6,
        nombre = "Dra. Marian Soto",
        especialidad = "Medicina Deportiva y Rehabilitación",
        descripcion = "Ayuda a perros de alto rendimiento y a mascotas en recuperación de lesiones. Ofrece terapias físicas y acupuntura.",
        calificacion = 5.0f,
        totalResenas = 65,
        imagenResId = R.drawable.enfermera_ultima
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VeterinarioScreen(
    onBackClick: () -> Unit,
    onVetClick: (Int) -> Unit
) {
    var textoBusqueda by remember { mutableStateOf("") }
    val colorFondo = Color(0xFFE0F2F1)

    val veterinariosFiltrados = remember(textoBusqueda) {
        if (textoBusqueda.isBlank()) {
            veterinariosDeEjemplo
        } else {
            veterinariosDeEjemplo.filter { veterinario ->
                val nombreCoincide = veterinario.nombre.contains(textoBusqueda, ignoreCase = true)
                val especialidadCoincide = veterinario.especialidad.contains(textoBusqueda, ignoreCase = true)
                nombreCoincide || especialidadCoincide
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Veterinarios", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = petSafe)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorFondo)
                .padding(paddingValues)
        ) {
            // Sección de Título y Búsqueda
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Encuentra a tu veterinario de confianza",
                    style = MaterialTheme.typography.headlineSmall
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedTextField(
                        value = textoBusqueda,
                        onValueChange = { textoBusqueda = it },
                        placeholder = { Text("Buscar por nombre o especialidad") },
                        modifier = Modifier.weight(1f),
                        leadingIcon = { Icon(Icons.Default.Search, "Icono de búsqueda") },
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(containerColor = Color.White)
                    )
                    Button(onClick = { /* Búsqueda automática */ }, enabled = false) {
                        Text("Buscar")
                    }
                }
            }

            // Lista de Veterinarios
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(veterinariosFiltrados) { veterinario ->
                    VeterinarioCard(
                        veterinario = veterinario,
                        onReservarClick = { onVetClick(veterinario.id) }
                    )
                }
            }
        }
    }
}

// --- INICIO DE LA CORRECCIÓN ---
@Composable
fun VeterinarioCard(veterinario: Veterinario, onReservarClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // 1. Se añade el Composable 'Image' que faltaba.
            Image(
                painter = painterResource(id = veterinario.imagenResId),
                contentDescription = "Foto de ${veterinario.nombre}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop // 'Crop' hace que la imagen llene el espacio sin deformarse
            )

            // 2. El resto del contenido de la tarjeta va debajo de la imagen.
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(veterinario.nombre, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Text(veterinario.especialidad, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                Text(veterinario.descripcion, style = MaterialTheme.typography.bodyMedium, maxLines = 3) // Limita las líneas para que no sea muy largo

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    RatingBar(rating = veterinario.calificacion)
                    Text(
                        text = "${veterinario.calificacion} (${veterinario.totalResenas} reseñas)",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onReservarClick,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Reservar cita")
                }
            }
        }
    }
}
// --- FIN DE LA CORRECCIÓN ---

@Composable
fun RatingBar(rating: Float, stars: Int = 5, starColor: Color = Color(0xFFFFC107)) {
    Row {
        val fullStars = kotlin.math.floor(rating).toInt()
        val emptyStars = stars - fullStars
        repeat(fullStars) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = starColor)
        }
        repeat(emptyStars) {
            Icon(Icons.Filled.StarBorder, contentDescription = null, tint = starColor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VeterinarioScreenPreview() {
    BaseProjectTheme {
        VeterinarioScreen(
            onBackClick = { },
            onVetClick = { }
        )
    }
}
