package com.example.baseproject.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.baseproject.ui.navigation.ShowcaseRoutes

/**
 * Modelo de datos que representa una categoría de componentes en el showcase.
 * Cada categoría agrupa un conjunto de componentes relacionados de Material Design 3.
 *
 * @property title Título de la categoría que se mostrará en la UI
 * @property icon Ícono representativo de la categoría
 * @property route Ruta de navegación para esta categoría
 * @property description Descripción breve de lo que contiene la categoría
 */
data class ShowcaseCategory(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val description: String
)

/**
 * Lista de todas las categorías disponibles en el showcase.
 * Esta lista define la estructura de navegación principal de la aplicación.
 */
val showcaseCategories = listOf(
    ShowcaseCategory(
        title = "Index",
        icon = Icons.Default.Home,
        route = ShowcaseRoutes.INDEX,
        description = "Diseño de página principal estilo Netflix"
    ),
    ShowcaseCategory(
        title = "Buttons",
        icon = Icons.Default.TouchApp,
        route = ShowcaseRoutes.BUTTONS,
        description = "Botones, FABs, IconButtons y variantes"
    ),
    ShowcaseCategory(
        title = "Text Fields",
        icon = Icons.Default.Edit,
        route = ShowcaseRoutes.TEXT_FIELDS,
        description = "Campos de texto y sus variantes"
    ),
    ShowcaseCategory(
        title = "Cards",
        icon = Icons.Default.CreditCard,
        route = ShowcaseRoutes.CARDS,
        description = "Tarjetas y contenedores"
    ),
    ShowcaseCategory(
        title = "Lists",
        icon = Icons.Default.List,
        route = ShowcaseRoutes.LISTS,
        description = "Listas, LazyColumn y elementos"
    ),
    ShowcaseCategory(
        title = "Dialogs",
        icon = Icons.Default.Message,
        route = ShowcaseRoutes.DIALOGS,
        description = "Diálogos y alertas"
    ),
    ShowcaseCategory(
        title = "Navigation",
        icon = Icons.Default.Menu,
        route = ShowcaseRoutes.NAVIGATION,
        description = "Drawers y navegación lateral"
    ),
    ShowcaseCategory(
        title = "Bottom Sheets",
        icon = Icons.Default.SwipeUp,
        route = ShowcaseRoutes.BOTTOM_SHEETS,
        description = "Snackbars y Bottom Sheets"
    ),
    ShowcaseCategory(
        title = "App Bars",
        icon = Icons.Default.AppSettingsAlt,
        route = ShowcaseRoutes.APP_BARS,
        description = "TopAppBar, BottomAppBar y variantes"
    ),
    ShowcaseCategory(
        title = "Selection Controls",
        icon = Icons.Default.CheckBox,
        route = ShowcaseRoutes.SELECTION_CONTROLS,
        description = "Switches, Sliders, Checkboxes, RadioButtons"
    ),
    ShowcaseCategory(
        title = "Icons & Badges",
        icon = Icons.Default.Star,
        route = ShowcaseRoutes.ICONS,
        description = "Iconos, avatares y badges"
    ),
    ShowcaseCategory(
        title = "Theming",
        icon = Icons.Default.Palette,
        route = ShowcaseRoutes.THEMING,
        description = "Typography, Colors y Shapes"
    )
)
