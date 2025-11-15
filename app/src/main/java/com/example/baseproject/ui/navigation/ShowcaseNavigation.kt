package com.example.baseproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baseproject.ui.screens.MainScreen
import com.example.baseproject.ui.screens.appbars.AppBarsScreen
import com.example.baseproject.ui.screens.bottomsheets.BottomSheetsScreen
import com.example.baseproject.ui.screens.buttons.ButtonsScreen
import com.example.baseproject.ui.screens.cards.CardsScreen
import com.example.baseproject.ui.screens.dialogs.DialogsScreen
import com.example.baseproject.ui.screens.icons.IconsScreen
import com.example.baseproject.ui.screens.lists.ListsScreen
import com.example.baseproject.ui.screens.login.LoginScreen
import com.example.baseproject.ui.screens.navigation.NavigationScreen
import com.example.baseproject.ui.screens.selectioncontrols.SelectionControlsScreen
import com.example.baseproject.ui.screens.textfields.TextFieldsScreen
import com.example.baseproject.ui.screens.theming.ThemingScreen
import com.example.baseproject.ui.screens.index.IndexScreen

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
    const val INDEX = "index"
    const val LOGIN = "login" // 1. Nueva ruta para el registro
}

@Composable
fun ShowcaseNavigation(
    navController: NavHostController = rememberNavController()
) {
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

        composable(route = ShowcaseRoutes.INDEX) {
            IndexScreen(
                onLoginClick = { // 2. Ahora navega a la pantalla de login/registro
                    navController.navigate(ShowcaseRoutes.LOGIN)
                }
            )
        }

        // 3. Nueva pantalla de Registro
        composable(route = ShowcaseRoutes.LOGIN) {
            LoginScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegisterClick = {
                    // Al registrarse, te envía al Index
                    navController.navigate(ShowcaseRoutes.INDEX)
                }
            )
        }

        // --- El resto de las pantallas del showcase ---
        composable(ShowcaseRoutes.BUTTONS) {
            ButtonsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.TEXT_FIELDS) {
            TextFieldsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.CARDS) {
            CardsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.LISTS) {
            ListsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.DIALOGS) {
            DialogsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.NAVIGATION) {
            NavigationScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.BOTTOM_SHEETS) {
            BottomSheetsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.APP_BARS) {
            AppBarsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.SELECTION_CONTROLS) {
            SelectionControlsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.ICONS) {
            IconsScreen(onBackClick = { navController.popBackStack() })
        }
        composable(ShowcaseRoutes.THEMING) {
            ThemingScreen(onBackClick = { navController.popBackStack() })
        }
    }
}