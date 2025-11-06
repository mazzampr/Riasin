package com.frontend.riasin.ui.theme.voucher

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.RiasinTheme

data class Voucher(
    val id: String,
    val title: String,
    val description: String,
    val expiryDate: String,
    val discountPercent: Int = 0,
    val discountAmount: Int = 0
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VoucherKuScreen(
    onBackClick: () -> Unit = {},
    onVoucherSelected: (Voucher) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedVoucherId by remember { mutableStateOf<String?>(null) }

    // Sample voucher data
    val vouchers = remember {
        listOf(
            Voucher(
                id = "1",
                title = "Tampil Cantik, Harga Asik!",
                description = "Diskon 10% untuk semua layanan make up!",
                expiryDate = "Berakhir: 31 Des 2025",
                discountPercent = 10
            ),
            Voucher(
                id = "2",
                title = "Promo Wisuda Spesial",
                description = "Diskon Rp 50.000 untuk paket wisuda",
                expiryDate = "Berakhir: 15 Des 2025",
                discountAmount = 50000
            )
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Voucher Ku",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFFAFAFA),
        bottomBar = {
            // Bottom action bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                shadowElevation = 8.dp
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
                            text = "Total voucher tersedia",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "1 voucher aktif",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }

                    Button(
                        onClick = {},
                        enabled = selectedVoucherId != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF7BACD),
                            disabledContainerColor = Color.LightGray
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.height(40.dp)
                    ) {
                        Text(
                            text = "Lihat Semua",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Coupon Code Input Section
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = Primary
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Coupon",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = "Got a coupon code? Enter here",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Voucher List
            vouchers.forEach { voucher ->
                VoucherCard(
                    voucher = voucher,
                    isSelected = selectedVoucherId == voucher.id,
                    onSelect = {
                        selectedVoucherId = voucher.id
                        onVoucherSelected(voucher)
                    },
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun VoucherCard(
    voucher: Voucher,
    isSelected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onSelect),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Left colored stripe/tab with primary color
            Box(
                modifier = Modifier
                    .width(30.dp)
                    .height(140.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Primary,
                    shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                ) {}
            }

            // Content area with pink background
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(140.dp),
                color = Color(0xFFFDE7ED),
                shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "\"${voucher.title}\"",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF424242),
                            fontSize = 16.sp,
                            lineHeight = 22.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = voucher.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF616161),
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = voucher.expiryDate,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF757575),
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = onSelect,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        ),
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier
                            .height(42.dp)
                            .widthIn(min = 85.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 8.dp
                        )
                    ) {
                        Text(
                            text = if (isSelected) "Dipilih" else "Pilih",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun VoucherKuScreenPreview() {
    RiasinTheme {
        VoucherKuScreen()
    }
}

