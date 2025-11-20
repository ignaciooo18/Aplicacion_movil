package com.example.baseproject.ui.navigation

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.baseproject.ui.screens.MainScreen
import com.example.baseproject.ui.screens.appbars.AppBarsScreen
import com.example.baseproject.ui.screens.bottomsheets.BottomSheetsScreen
import com.example.baseproject.ui.screens.buttons.ButtonsScreen
import com.example.baseproject.ui.screens.cards.CardsScreen
import com.example.baseproject.ui.screens.dialogs.DialogsScreen
import com.example.baseproject.ui.screens.icons.IconsScreen
import com.example.baseproject.ui.screens.index.IndexScreen
import com.example.baseproject.ui.screens.inicio.InicioScreen
import com.example.baseproject.ui.screens.lists.ListsScreen
import com.example.baseproject.ui.screens.login.LoginScreen
import com.example.baseproject.ui.screens.navigation.NavigationScreen
import com.example.baseproject.ui.screens.selectioncontrols.SelectionControlsScreen
import com.example.baseproject.ui.screens.textfields.TextFieldsScreen
import com.example.baseproject.ui.screens.theming.ThemingScreen
import com.example.baseproject.ui.screens.veterinariodet.VeterinarioDetalleScreen
import com.example.baseproject.ui.screens.veterinarios.VeterinarioScreen

// 💡 IMPORTACIONES NECESARIAS PARA TUS PANTALLAS Y MOCKS
import com.example.baseproject.ui.screens.products.* import com.example.baseproject.ui.screens.profile.*
import com.example.baseproject.ui.screens.reviews.*
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


// MOCKS DE DATOS (Asumimos que están accesibles aquí)
val mockUserForNav = mockUser
val mockPetForNav = mockPet
val mockProductsForNav = mockProducts

object ShowcaseRoutes {
    // Rutas originales
    const val MAIN = "main"
    const val BUTTONS = "buttons"
    const val TEXT_FIELDS = "textfields"
    const val CARDS = "cards"
    const val LISTS = "lists"
    const val DIALOGS = "dialogs"
    const val NAVIGATION = "navigation"
    const val BOTTOM_SHEETS = "bottomsheets"
    const val APP_BARS = "appbars"
    const val SELECTION_CONTROLS = "selectioncontrols"
    const val ICONS = "icons"
    const val THEMING = "theming"
    const val LOGIN = "login"
    const val INDEX = "index"
    const val INICIO = "inicio"
    const val VETERINARIOS = "veterinarios"
    const val VETERINARIO_DETALLE = "veterinarios/{vetId}"

    // TUS NUEVAS RUTAS Y RUTAS DE NACHO
    const val PRODUCTS = "products_route"
    const val PROFILE = "profile_route"
    const val ADD_REVIEW = "add_review_route"
    const val EDIT_PROFILE = "edit_profile_route"
    const val REGISTER = "register_route"
    const val VER_VETERINARIOS = "ver_veterinarios_route"
    const val AGENDAR_CITA = "agendar_veterinario_route"
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowcaseNavigation(
    navController: NavHostController = rememberNavController()
) {
    // 💡 LÓGICA PARA EL ACCESO A GALERÍA/ARCHIVOS (Resuelve el Punto 2)
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    NavHost(
        navController = navController,
        startDestination = ShowcaseRoutes.MAIN
    ) {
        composable(ShowcaseRoutes.MAIN) {
            MainScreen(
                onCategoryClick = { route ->
                    navController.navigate(route)
                }
            )
        }

        // ----------------------------------------------------
        // 1. PANTALLAS IMPLEMENTADAS POR TI (CON LOGOUT Y GALERÍA)
        // ----------------------------------------------------

        // PANTALLA DE PERFIL
        composable(ShowcaseRoutes.PROFILE) {
            ProfileScreen(
                user = mockUserForNav,
                pet = mockPetForNav,
                onLogoutClick = {
                    navController.navigate(ShowcaseRoutes.LOGIN) {
                        popUpTo(ShowcaseRoutes.MAIN) { inclusive = true } // LOGOUT SEGURO
                    }
                },
                onEditProfileClick = { navController.navigate(ShowcaseRoutes.EDIT_PROFILE) }
            )
        }

        // PANTALLA DE PRODUCTOS
        composable(ShowcaseRoutes.PRODUCTS) {
            ProductsScreen(
                products = mockProductsForNav,
                onProductClick = { /* Acción para ir al detalle */ },
                onSearch = { query -> /* Lógica de búsqueda */ },
                onLogoutClick = {
                    navController.navigate(ShowcaseRoutes.LOGIN) {
                        popUpTo(ShowcaseRoutes.MAIN) { inclusive = true } // LOGOUT SEGURO
                    }
                }
            )
        }

        // PANTALLA AÑADIR RESEÑA
        composable(ShowcaseRoutes.ADD_REVIEW) {
            AddReviewScreen(
                onSubmitReview = { _, _, _, _, _ -> /* Lógica de envío */ },
                onCancel = { navController.popBackStack() },
                onLogoutClick = {
                    navController.navigate(ShowcaseRoutes.LOGIN) {
                        popUpTo(ShowcaseRoutes.MAIN) { inclusive = true } // LOGOUT SEGURO
                    }
                },
                onOpenDrawer = { /* Lógica para abrir el drawer */ },
                onSelectFile = {
                    // ACCIÓN DE GALERÍA: Lanza el selector de archivos
                    imagePickerLauncher.launch("image/*")
                }
            )
        }

        // ----------------------------------------------------
        // 2. RUTAS PENDIENTES/DE NACHO (PLACEHOLDERS)
        // ----------------------------------------------------
        composable(ShowcaseRoutes.EDIT_PROFILE) { /* EditProfileScreen Placeholder */ }
        composable(ShowcaseRoutes.REGISTER) { /* RegisterScreen Placeholder */ }
        composable(ShowcaseRoutes.VER_VETERINARIOS) { /* VerVeterinariosScreen Placeholder */ }
        composable(ShowcaseRoutes.AGENDAR_CITA) { /* AgendarCitaScreen Placeholder */ }

        // ----------------------------------------------------
        // 3. RUTAS ORIGINALES Y DE NACHO (COMPLETAS)
        // ----------------------------------------------------

        composable(ShowcaseRoutes.INDEX) {
            IndexScreen(
                onLoginClick = { navController.navigate(ShowcaseRoutes.LOGIN) }
            )
        }

        composable(ShowcaseRoutes.INICIO) {
            InicioScreen(
                onLoginClick = { navController.navigate(ShowcaseRoutes.LOGIN) },
                onReviewsClick = { navController.navigate(ShowcaseRoutes.ADD_REVIEW) },
                onVetsClick = { navController.navigate(ShowcaseRoutes.VETERINARIOS) },
                onProductsClick = { navController.navigate(ShowcaseRoutes.PRODUCTS) }
            )
        }

        composable(route = ShowcaseRoutes.VETERINARIOS) {
            VeterinarioScreen(
                onBackClick = { navController.popBackStack() },
                onVetClick = { vetId ->
                    navController.navigate("veterinarios/$vetId")
                }
            )
        }

        composable(
            route = ShowcaseRoutes.VETERINARIO_DETALLE,
            arguments = listOf(navArgument("vetId") { type = NavType.IntType })
        ) { backStackEntry ->
            val vetId = backStackEntry.arguments?.getInt("vetId")
            if (vetId != null) {
                VeterinarioDetalleScreen(
                    vetId = vetId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }

        composable(route = ShowcaseRoutes.LOGIN) {
            LoginScreen(
                onBackClick = { navController.popBackStack() },
                onRegisterClick = {
                    navController.navigate(ShowcaseRoutes.INICIO) {
                        popUpTo(ShowcaseRoutes.MAIN)
                    }
                }
            )
        }

        // --- El resto de las pantallas originales del showcase ---
        composable(ShowcaseRoutes.BUTTONS) { ButtonsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.TEXT_FIELDS) { TextFieldsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.CARDS) { CardsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.LISTS) { ListsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.DIALOGS) { DialogsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.NAVIGATION) { NavigationScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.BOTTOM_SHEETS) { BottomSheetsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.APP_BARS) { AppBarsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.SELECTION_CONTROLS) { SelectionControlsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.ICONS) { IconsScreen(onBackClick = { navController.popBackStack() }) }
        composable(ShowcaseRoutes.THEMING) { ThemingScreen(onBackClick = { navController.popBackStack() }) }
    }
}
