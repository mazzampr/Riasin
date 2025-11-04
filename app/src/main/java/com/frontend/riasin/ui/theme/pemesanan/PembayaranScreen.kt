package com.frontend.riasin.ui.theme.pemesanan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import com.frontend.riasin.R
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.RiasinTheme
import com.frontend.riasin.ui.theme.voucher.Voucher
import com.frontend.riasin.ui.theme.payment.PaymentConfirmationDialog

// Custom colors for this screen
private val LightPink = Color(0xFFFDE7ED)
private val IconBackgroundPink = Color(0xFFE8C5D0)
private val ButtonPink = Color(0xFFF7BACD)
private val WarningBackground = Color(0xFFF5F5F5)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembayaranScreen(
    muaName: String = "Laraz Makeup",
    selectedVoucher: Voucher? = null,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onVoucherClick: () -> Unit = {},
    onKonfirmasi: () -> Unit = {}
) {
    // Calculate prices
    val totalPrice = 400000
    val discountAmount = when {
        selectedVoucher != null && selectedVoucher.discountPercent > 0 ->
            (totalPrice * selectedVoucher.discountPercent / 100)
        selectedVoucher != null && selectedVoucher.discountAmount > 0 ->
            selectedVoucher.discountAmount
        else -> 0
    }
    val finalTotal = totalPrice - discountAmount
    val dpAmount = finalTotal / 2

    var showConfirmDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pembayaran",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFFAFAFA) // Light grey background
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Spacer for profile section offset
            Spacer(modifier = Modifier.height(50.dp))

            // Main Container with Pink Background and Padding
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(LightPink)
                        .padding(20.dp)
                ) {
                    // Space for profile image that slides outside
                    Spacer(modifier = Modifier.height(50.dp))

                    // Profile Name
                    Text(
                        text = muaName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    // Detail Items Section
                    DetailItem(
                        icon = Icons.Default.Star,
                        label = "Layanan",
                        value = "Paket Wisuda"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        icon = Icons.Default.DateRange,
                        label = "Tanggal",
                        value = "20 Juli 2025"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        icon = Icons.Default.Settings,
                        label = "Jam",
                        value = "07.00 WIB"
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        icon = Icons.Default.LocationOn,
                        label = "Lokasi",
                        value = "Jl. Cempaka No.9, RT...",
                        hasArrow = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    DetailItem(
                        icon = Icons.Default.Person,
                        label = "Jumlah Orang",
                        value = "1 Orang"
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    // Voucher/Kupon Section
                    Text(
                        text = "Voucher/Kupon",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(onClick = onVoucherClick),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF0F0F0)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Voucher",
                                tint = Primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = if (selectedVoucher != null) {
                                    if (selectedVoucher.discountPercent > 0) {
                                        "Diskon ${selectedVoucher.discountPercent}%"
                                    } else {
                                        "Diskon Rp ${selectedVoucher.discountAmount / 1000}rb"
                                    }
                                } else {
                                    "Voucher"
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (selectedVoucher != null) FontWeight.Bold else FontWeight.Medium,
                                color = Color.Black,
                                fontSize = 14.sp
                            )
                            Text(
                                text = if (selectedVoucher != null) {
                                    "1 voucher digunakan"
                                } else {
                                    "Gunakan/Masukkan Kode"
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Spacer(modifier = Modifier.height(16.dp))

                    // Total Harga Section
                    // Total Harga Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Total Harga",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            fontSize = 16.sp
                        )

                        Text(
                            text = "Rp ${String.format("%,d", totalPrice).replace(',', '.')}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 20.sp
                        )
                    }

                    // Show discount if voucher is applied
                    if (selectedVoucher != null && discountAmount > 0) {
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Diskon",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )

                            Text(
                                text = "- Rp ${String.format("%,d", discountAmount).replace(',', '.')}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4CAF50),
                                fontSize = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Pembayaran DP Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Pembayaran DP",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "50%",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }

                        Text(
                            text = "Rp ${String.format("%,d", dpAmount).replace(',', '.')}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red,
                            fontSize = 18.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Warning Box
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(WarningBackground)
                            .border(
                                width = 1.dp,
                                color = Color.LightGray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Color.Gray,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Pelunasan wajib dilakukan H-1 pelaksanaan.\nDP Hangus jika melakukan pembatalan pesanan",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Konfirmasi Button
                    Button(
                        onClick = { showConfirmDialog = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonPink),
                        shape = RoundedCornerShape(28.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Konfirmasi & Bayar Sekarang",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Rp. ${String.format("%,d", dpAmount).replace(',', '.')} - DP",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                        }
                    }
                }

                // Profile Image - positioned absolutely to slide outside container
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .offset(y = (-50).dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(4.dp), // White border effect
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_laraz),
                            contentDescription = muaName,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // Show confirmation dialog
    if (showConfirmDialog) {
        PaymentConfirmationDialog(
            onDismiss = { showConfirmDialog = false },
            onConfirm = {
                showConfirmDialog = false
                onKonfirmasi()
            },
            amount = "Rp. ${String.format("%,d", dpAmount).replace(',', '.')} - DP"
        )
    }
}

@Composable
fun DetailItem(
    icon: ImageVector,
    label: String,
    value: String,
    hasArrow: Boolean = false,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon container with rounded corners
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(IconBackgroundPink),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Label and Value
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 14.sp
            )
        }

        // Arrow for location
        if (hasArrow) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Details",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun PembayaranScreenPreview() {
    RiasinTheme {
        PembayaranScreen()
    }
}

