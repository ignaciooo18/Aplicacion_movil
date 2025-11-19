package com.example.baseproject.ui.screens.inicio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseproject.ui.theme.BaseProjectTheme
import com.example.baseproject.ui.theme.petSafe



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    // 2. Se reemplaza onCardClick por lambdas específicos para cada tipo de tarjeta.
    onReviewsClick: () -> Unit,
    onVetsClick: () -> Unit,
    onProductsClick: () -> Unit,
    onLoginClick: () -> Unit // Este se mantiene como estaba.
) {
    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pet Society", color = Color.White) },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Más opciones",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Configuración") },
                            onClick = { /* TODO */ }
                        )
                        DropdownMenuItem(
                            text = { Text("Perfil") },
                            onClick = {}
                        )
                        DropdownMenuItem(
                            text = { Text("Cerrar Sesión") },
                            onClick = { /* TODO */ }
                        )
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
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "¡Hola de nuevo!",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Aquí tienes un resumen de la comunidad:",
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }

            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.height(750.dp)
                ) {
                    // 3. Se asigna el lambda de clic correcto a cada tarjeta.
                    item { ReviewCard(onClick = onReviewsClick) }
                    item { VetRatingCard(onClick = onVetsClick) }
                    item { ProductDiscountCard(onClick = onProductsClick) }
                    item { ReviewCard(onClick = onReviewsClick) }
                    item { VetRatingCard(onClick = onVetsClick) }
                    item { ProductDiscountCard(onClick = onProductsClick) }
                    item { ReviewCard(onClick = onReviewsClick) }
                    item { VetRatingCard(onClick = onVetsClick) }
                    item { ProductDiscountCard(onClick = onProductsClick) }
                }
            }
        }
    }
}


// --- INICIO DE TARJETAS (Sin cambios aquí) ---
// La estructura interna de las tarjetas ya está preparada para recibir un lambda `onClick`,
// por lo que no es necesario modificarlas.

@Composable
private fun ReviewCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Usuario", modifier = Modifier.size(24.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Ana M.", style = MaterialTheme.typography.titleSmall)
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "\"¡El mejor paseador! Mi perro Max vuelve feliz y cansado. Totalmente recomendado.\"",
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                )
            }
            Text(
                text = "Ver más",
                color = petSafe,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End)
                    .clickable(onClick = onClick)
            )
        }
    }
}


@Composable
private fun VetRatingCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(12.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("Veterinaria 'Sanitos'", style = MaterialTheme.typography.titleSmall)
                Text("Calificación", style = MaterialTheme.typography.labelSmall)
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val starColor = Color(0xFFFFC107)
                    repeat(4) { Icon(Icons.Filled.Star, contentDescription = null, tint = starColor, modifier = Modifier.size(16.dp)) }
                    Icon(Icons.Filled.StarBorder, contentDescription = null, tint = starColor, modifier = Modifier.size(16.dp))
                }
            }
            Text(
                text = "Ver más",
                color = petSafe,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End)
                    .clickable(onClick = onClick)
            )
        }
    }
}

@Composable
private fun ProductDiscountCard(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("¡Oferta!", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onTertiaryContainer)
                Spacer(Modifier.height(8.dp))
                Text("Comida 10kg", style = MaterialTheme.typography.bodyMedium, maxLines = 1)
                Spacer(Modifier.height(4.dp))
                Text("$19.990", style = MaterialTheme.typography.titleSmall)
            }
            Text(
                text = "Ver más",
                color = petSafe,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.End)
                    .clickable(onClick = onClick)
            )
        }
    }
}

// 4. Se actualiza el Preview para que coincida con la nueva firma de la función.
@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    BaseProjectTheme {
        InicioScreen(
            onReviewsClick = {},
            onVetsClick = {},
            onProductsClick = {},
            onLoginClick = {}
        )
    }
}
