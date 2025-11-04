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
    object VoucherKu : Screen("voucher_ku")
    object PilihMetodePembayaran : Screen("pilih_metode_pembayaran/{amount}")
    object QrisPayment : Screen("qris_payment/{amount}")
    object PaymentSuccess : Screen("payment_success")
}
