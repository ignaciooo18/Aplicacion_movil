package com.example.baseproject.ui.screens.index

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.baseproject.ui.theme.BaseProjectTheme
import com.example.baseproject.ui.theme.petSafe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IndexScreen(
    onLoginClick: () -> Unit // Acción para cuando se presiona el botón de login
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pet Society", color = Color.White) },
                actions = {
                    TextButton(onClick = onLoginClick) {
                        Text("Registrate", color = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = petSafe
                )
            )
        }
    ) { paddingValues ->
        // Usamos LazyColumn para que toda la pantalla sea "scrollable" verticalmente
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 24.dp)
        )
        {
            // Sección de bienvenida
            item {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "¡Bienvenido a Pet Society!",
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Todo lo que necesitas para tu mejor amigo, en un solo lugar.",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            // Sección "Nuestros Servicios"
            item {
                Column {
                    Text(
                        text = "Nuestros Servicios",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            FeatureCard(
                                title = "Paseadores/cuidadores",
                                icon = Icons.Default.Pets,
                                onClick = {}
                            )
                        }
                        item {
                            FeatureCard(
                                title = "Veterinarios",
                                icon = Icons.Default.LocalHospital,
                                onClick = {}
                            )
                        }
                        item {
                            FeatureCard(
                                title = "Tiendas",
                                icon = Icons.Default.ShoppingCart,
                                onClick = {}
                            )
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(24.dp)) }

            // Sección "Comunidad"
            item {
                Column {
                    Text(
                        text = "Comunidad",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp)
                    ) {
                        item {
                            FeatureCard(
                                title = "Reseñas",
                                icon = Icons.Default.RateReview,
                                onClick = {}
                            )
                        }
                        item {
                            FeatureCard(
                                title = "Adopciones",
                                icon = Icons.Default.Pets,
                                onClick = {}
                            )
                        }
                    }
                }
            }


            item { Spacer(modifier = Modifier.height(32.dp)) }

            // 2. Añadimos el nuevo texto como un item en la LazyColumn
            item {
                Text(
                    text = "¡Logeate y descubre más funciones!",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp) // Padding para que no ocupe toda la línea
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeatureCard(title: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.width(140.dp)
    )
    {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BaseProjectTheme {
        IndexScreen(
            onLoginClick = {}
        )
    }
}
