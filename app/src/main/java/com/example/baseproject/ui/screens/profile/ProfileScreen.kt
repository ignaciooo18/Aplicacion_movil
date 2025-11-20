package com.example.baseproject.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

//MODELOS DE DATOS
data class UserProfile(
    val name: String,
    val age: String,
    val email: String,
    val avatarResId: Int?
)

data class PetProfile(
    val name: String,
    val type: String,
    val age: String,
    val description: String,
    val photoResId: Int?
)


val mockUser = UserProfile(
    name = "María González",
    age = "24 años",
    email = "maria.gonzalez@gmail.com",
    avatarResId = R.drawable.maria_avatar
)

val mockPet = PetProfile(
    name = "Luna",
    type = "Gato",
    age = "2 años",
    description = "Le encanta dormir sobre teclados",
    photoResId = R.drawable.luna_photo
)

//COMPOSABLES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    user: UserProfile,
    pet: PetProfile,
    onLogoutClick: () -> Unit,
    //Función para ir a la pantalla "editar perfil"
    onEditProfileClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil de Usuario", color = Color.White) },
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
            contentPadding = PaddingValues(top = 16.dp, bottom = 64.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    //Información Personal
                    ProfileCard(
                        title = "Información Personal",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    ) {

                        UserContent(user = user, onEditProfileClick = onEditProfileClick)
                    }

                    //Mi Mascota
                    ProfileCard(
                        title = "Mi Mascota",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        PetContent(pet = pet)
                    }
                }
            }
        }
    }
}

//Composable para la estructura de las tarjetas
@Composable
fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

//Contenido de la tarjeta de el usuario
@Composable
fun UserContent(user: UserProfile, onEditProfileClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.size(90.dp),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Image(
                painter = painterResource(id = user.avatarResId ?: R.drawable.ic_launcher_foreground),
                contentDescription = "Avatar de Usuario",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        ProfileInfoField(label = "Nombre", value = user.name)
        ProfileInfoField(label = "Edad", value = user.age)
        ProfileInfoField(label = "Correo", value = user.email)

        Spacer(modifier = Modifier.height(24.dp))

        //Btoón "editar perfil"
        Button(
            onClick = onEditProfileClick,
            modifier = Modifier.fillMaxWidth(0.8f) // Lo hacemos un poco más angosto para centrarlo
        ) {
            Text("Editar Perfil")
        }

    }
}

//Contenido de la tarjeta de la mascota
@Composable
fun PetContent(pet: PetProfile) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
        ) {
            Image(
                painter = painterResource(id = pet.photoResId ?: R.drawable.ic_launcher_foreground),
                contentDescription = "Foto de Mascota",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        ProfileInfoField(label = "Tipo", value = pet.type)
        ProfileInfoField(label = "Edad", value = pet.age)

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = petSafe)
        ) {
            Text("Datos curiosos")
        }
        Text(pet.description, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 4.dp))
    }
}


@Composable
fun ProfileInfoField(label: String, value: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
        Divider(modifier = Modifier.padding(top = 4.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BaseProjectTheme {
        ProfileScreen(
            user = mockUser,
            pet = mockPet,
            onLogoutClick = {},
            onEditProfileClick = {}
        )
    }
}