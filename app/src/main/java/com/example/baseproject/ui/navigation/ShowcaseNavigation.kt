package com.example.baseproject.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
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

object ShowcaseRoutes {
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
}

// --- INICIO DE LA CORRECCIÓN ---
// 1. La anotación se aplica a la función COMPLETA que contiene la llamada peligrosa.
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ShowcaseNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ShowcaseRoutes.INDEX // Recomiendo empezar en INICIO para probar el flujo completo
    ) {
        composable(ShowcaseRoutes.MAIN) { // Esta es la pantalla original del showcase
            MainScreen(
                onCategoryClick = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(route = ShowcaseRoutes.INICIO) {
            InicioScreen(
                onLoginClick = { navController.navigate(ShowcaseRoutes.LOGIN) },
                onReviewsClick = { /* navController.navigate("Ruta_de_Reseñas") */ },
                onVetsClick = { navController.navigate(ShowcaseRoutes.VETERINARIOS) },
                onProductsClick = { /* navController.navigate("Ruta_de_Ofertas") */ }
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

        composable(route = ShowcaseRoutes.INDEX) {
            IndexScreen(
                onLoginClick = { navController.navigate(ShowcaseRoutes.LOGIN) }
            )
        }

        // 2. Ya no se necesita la anotación aquí.
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
        // --- FIN DE LA CORRECCIÓN ---

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

        // --- El resto de las pantallas del showcase (sin cambios) ---
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
