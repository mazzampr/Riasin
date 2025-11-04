package com.frontend.riasin.ui.theme.pemesanan

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.frontend.riasin.R
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.PrimaryLight
import com.frontend.riasin.ui.theme.PrimaryLight2
import com.frontend.riasin.ui.theme.RiasinTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PembayaranScreen(
    muaName: String = "Laraz Makeup",
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onKonfirmasi: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pembayaran", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = Color.White
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // MUA Profile Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = PrimaryLight2),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Profile Image
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_laraz),
                            contentDescription = muaName,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = muaName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }

            // Booking Details Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                BookingDetailItem(
                    icon = Icons.Default.Star,
                    title = "Layanan",
                    value = "Paket Wisuda"
                )

                Spacer(modifier = Modifier.height(12.dp))

                BookingDetailItem(
                    icon = Icons.Default.DateRange,
                    title = "Tanggal",
                    value = "20 Juli 2025"
                )

                Spacer(modifier = Modifier.height(12.dp))

                BookingDetailItem(
                    icon = Icons.Default.Settings,
                    title = "Jam",
                    value = "07.00 WIB"
                )

                Spacer(modifier = Modifier.height(12.dp))

                BookingDetailItem(
                    icon = Icons.Default.LocationOn,
                    title = "Lokasi",
                    value = "Jl. Cempaka No.9, RT...",
                    hasArrow = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                BookingDetailItem(
                    icon = Icons.Default.Person,
                    title = "Jumlah Orang",
                    value = "1 Orang"
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Voucher Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Voucher/Kupon",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Voucher",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "Gunakan/Masukkan Kode",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Voucher",
                        tint = Primary,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Price Details Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Total Harga",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    PriceRow(label = "Pembayaran DP 50%", amount = "Rp 200.000")

                    Spacer(modifier = Modifier.height(16.dp))

                    Divider(color = Color.LightGray)

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = Primary,
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Pelunasan wajib dilakukan H-1 pelaksanaan.\nDP Harga Asli maksimal pembatalan pesanan.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Konfirmasi Button
            Button(
                onClick = onKonfirmasi,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Konfirmasi & Bayar Sekarang",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // DP Text
            Text(
                text = "Rp 200.000 • DP",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = Primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun BookingDetailItem(
    icon: ImageVector,
    title: String,
    value: String,
    hasArrow: Boolean = false,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PrimaryLight2),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(PrimaryLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Primary,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            if (hasArrow) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Details",
                    tint = Primary
                )
            }
        }
    }
}

@Composable
fun PriceRow(
    label: String,
    amount: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Text(
            text = amount,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = Primary
        )
    }
}

@Preview
@Composable
private fun PembayaranScreenPreview() {
    RiasinTheme {
        PembayaranScreen()
    }
}

