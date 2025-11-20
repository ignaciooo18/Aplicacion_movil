package com.example.baseproject.ui.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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


// MODELOS DE DATOS (Se mantienen intactos)
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageResId: Int
)

// MOCK DATA (Se mantienen intactos)
val mockProducts = listOf(
    Product(
        id = 1,
        name = "Simparica Saronaler 40 mg (10 a 20 kg)",
        description = "Lo más confiable y accesible para el cuidado de la mascota.",
        price = "Precio no disponible",
        imageResId = R.drawable.simparica
    ),
    Product(
        id = 2,
        name = "Mebermic Comprimido Oral",
        description = "El mejor antiparasitario para el bienestar de las mascotas.",
        price = "Precio no disponible",
        imageResId = R.drawable.mebermic
    ),
    Product(
        id = 3,
        name = "NexGard Afoxolaner 28,3 mg",
        description = "Más barato y afoxolaner al animal, ideal para el control de pulgas y garrapatas.",
        price = "Precio no disponible",
        imageResId = R.drawable.nexgard
    ),
    Product(
        id = 4,
        name = "Cotonitos Ultra Suave 400 u",
        description = "Cotonitos extra suaves, ideales para la higiene diaria.",
        price = "Precio no disponible",
        imageResId = R.drawable.cotonitos
    ),
    Product(
        id = 5,
        name = "Shampoo Medicado (Botella)",
        description = "Shampoo medicado para pelaje sensible y tratamiento de la piel.",
        price = "Precio no disponible",
        imageResId = R.drawable.shampoo
    ),
    Product(
        id = 6,
        name = "ARTRI-TAB Suplemento",
        description = "Suplemento nutricional para la salud articular en perros y gatos.",
        price = "Precio no disponible",
        imageResId = R.drawable.artri_tab
    )
)

// COMPOSABLES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    products: List<Product>, // Lista completa que recibe de la navegación
    onProductClick: (Product) -> Unit,
    onSearch: (String) -> Unit, // Mantenemos la función, pero hacemos el filtro local
    onLogoutClick: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    // 💡 LÓGICA DE FILTRADO FUNCIONAL
    val filteredProducts = remember(searchText, products) {
        if (searchText.isBlank()) {
            products
        } else {
            products.filter {
                it.name.contains(searchText, ignoreCase = true) ||
                        it.description.contains(searchText, ignoreCase = true)
            }
        }
    }
    // ------------------------------------

    Scaffold(
        topBar = {
            TopAppBar(
                // Título original (ya corregido el problema de corte en el TopAppBar)
                title = { Text("Encuentra tu producto más barato", color = Color.White) },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // 1.- Barra de Búsqueda
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it // 💡 Actualiza el estado, lo que activa el filtro
                        // onSearch(it)
                    },
                    label = { Text("Ingresa tu producto") },
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // El botón solo llama a la acción de búsqueda (la lógica ya está en el remember)
                Button(onClick = { onSearch(searchText) }) {
                    Text("Buscar")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 2.- Productos
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                // 💡 USA LA LISTA FILTRADA
                items(filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product) }
                    )
                }
            }
        }
    }
}

// Composable para la tarjeta individual del producto (Se mantiene intacto)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Imagen del producto
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre y precio
            Text(
                text = product.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            // Descripción
            Text(
                text = product.description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                maxLines = 3,
                minLines = 3,
                modifier = Modifier.padding(top = 4.dp, start = 4.dp, end = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botón "Ver Detalle"
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = petSafe)
            ) {
                Text("Ver Detalle")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    BaseProjectTheme {
        ProductsScreen(
            products = mockProducts,
            onProductClick = {},
            onSearch = {},
            onLogoutClick = {}
        )
    }
}