package com.frontend.riasin

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.frontend.riasin.ui.theme.Gray
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.RiasinTheme
import com.frontend.riasin.ui.theme.chat.ChatScreen
import com.frontend.riasin.ui.theme.detail.DetailLarazScreen
import com.frontend.riasin.ui.theme.detail.DetailMarisaScreen
import com.frontend.riasin.ui.theme.detail.DetailNajaScreen
import com.frontend.riasin.ui.theme.history.HistoryScreen
import com.frontend.riasin.ui.theme.home.HomeScreen
import com.frontend.riasin.ui.theme.pemesanan.BookingScreen
import com.frontend.riasin.ui.theme.pemesanan.Pemesanan1Screen
import com.frontend.riasin.ui.theme.pemesanan.Pemesanan2Screen
import com.frontend.riasin.ui.theme.pemesanan.PembayaranScreen
import com.frontend.riasin.ui.theme.profile.ProfileScreen
import com.mazzampr.githubcompose.ui.navigation.NavigationItem
import com.mazzampr.githubcompose.ui.navigation.Screen

@Composable
fun RiasinApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine if bottom bar should be shown - hide for all detail screens
    val showBottomBar = currentRoute != Screen.DetailLaraz.route &&
            currentRoute != Screen.DetailNaja.route &&
            currentRoute != Screen.DetailMarisa.route &&
            currentRoute != Screen.Pemesanan1.route &&
            currentRoute != Screen.Pemesanan2.route &&
            currentRoute != Screen.Booking.route &&
            currentRoute != Screen.Pembayaran.route

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onMUAClick = { muaId ->
                        when (muaId) {
                            "1" -> navController.navigate(Screen.DetailLaraz.route)
                            "2" -> navController.navigate(Screen.DetailNaja.route)
                            "3" -> navController.navigate(Screen.DetailMarisa.route)
                            else -> navController.navigate(Screen.DetailLaraz.route)
                        }
                    }
                )
            }
            composable(Screen.History.route) {
                HistoryScreen()
            }
            composable(Screen.Chat.route) {
                ChatScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.DetailLaraz.route) {
                DetailLarazScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onPesanSekarang = {
                        navController.navigate("pemesanan1/Laraz Makeup")
                    }
                )
            }
            composable(Screen.DetailNaja.route) {
                DetailNajaScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onPesanSekarang = {
                        navController.navigate("pemesanan1/Naja Wedding")
                    }
                )
            }
            composable(Screen.DetailMarisa.route) {
                DetailMarisaScreen(
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onPesanSekarang = {
                        navController.navigate("pemesanan1/Marisameidimua")
                    }
                )
            }
            composable(
                route = "pemesanan1/{muaName}",
                arguments = listOf(navArgument("muaName") { type = NavType.StringType })
            ) { backStackEntry ->
                val muaName = backStackEntry.arguments?.getString("muaName") ?: ""
                Pemesanan1Screen(
                    muaName = muaName,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onPilihLayanan = { mua, service ->
                        navController.navigate("pemesanan2/$mua")
                    }
                )
            }
            composable(
                route = "pemesanan2/{muaName}",
                arguments = listOf(navArgument("muaName") { type = NavType.StringType })
            ) { backStackEntry ->
                val muaName = backStackEntry.arguments?.getString("muaName") ?: ""
                Pemesanan2Screen(
                    muaName = muaName,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onPilihJadwal = { mua ->
                        navController.navigate("booking/$mua")
                    }
                )
            }
            composable(
                route = "booking/{muaName}",
                arguments = listOf(navArgument("muaName") { type = NavType.StringType })
            ) { backStackEntry ->
                val muaName = backStackEntry.arguments?.getString("muaName") ?: ""
                BookingScreen(
                    muaName = muaName,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onLanjut = { mua ->
                        navController.navigate("pembayaran/$mua")
                    }
                )
            }
            composable(
                route = "pembayaran/{muaName}",
                arguments = listOf(navArgument("muaName") { type = NavType.StringType })
            ) { backStackEntry ->
                val muaName = backStackEntry.arguments?.getString("muaName") ?: ""
                PembayaranScreen(
                    muaName = muaName,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onKonfirmasi = {
                        // Navigate to success screen or home
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        contentColor = Primary,
        tonalElevation = 20.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = R.drawable.ic_home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Booking",
                icon = R.drawable.ic_history,
                screen = Screen.History
            ),
            NavigationItem(
                title = "Chat",
                icon = R.drawable.ic_chat,
                screen = Screen.Chat
            ),
            NavigationItem(
                title = "Profile",
                icon = R.drawable.ic_profile,
                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = Primary,
                    unselectedIconColor = Gray,
                    selectedTextColor = Primary,
                    unselectedTextColor = Gray,
                    selectedIndicatorColor = Color.Transparent,
                    disabledIconColor = Color.LightGray,
                    disabledTextColor = Color.LightGray
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RiasinPreview() {
    RiasinTheme {
        RiasinApp()
    }
}