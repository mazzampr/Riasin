package com.mazzampr.githubcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object History : Screen("history")
    object Chat : Screen("chat")
    object Profile : Screen("profile")
    object DetailLaraz : Screen("detail/laraz")
    object DetailNaja : Screen("detail/naja")
    object DetailMarisa : Screen("detail/marisa")
    object Pemesanan1 : Screen("pemesanan1/{muaName}")
    object Pemesanan2 : Screen("pemesanan2/{muaName}")
    object Booking : Screen("booking/{muaName}")
    object Pembayaran : Screen("pembayaran/{muaName}")
}
