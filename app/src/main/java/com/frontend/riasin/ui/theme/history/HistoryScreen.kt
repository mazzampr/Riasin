package com.frontend.riasin.ui.theme.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frontend.riasin.R
import com.frontend.riasin.ui.theme.BackgroundColor
import com.frontend.riasin.ui.theme.Gray
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.PrimaryLight
import com.frontend.riasin.ui.theme.PrimaryLight2
import com.frontend.riasin.ui.theme.PrimaryLight3
import com.frontend.riasin.ui.theme.PrimaryLight4
import com.frontend.riasin.ui.theme.RiasinTheme

data class BookingItem(
    val id: String,
    val muaName: String,
    val imageRes: Int,
    val totalPrice: Int,
    val remainingPrice: Int,
    val scheduledDate: String,
    val scheduledTime: String,
    val status: BookingStatus
)

enum class BookingStatus {
    AKTIF, SELESAI, DIBATALKAN
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(BookingStatus.AKTIF) }

    // Sample data
    val bookings = remember {
        listOf(
            BookingItem(
                id = "1",
                muaName = "Laraz Makeup",
                imageRes = R.drawable.img_laraz,
                totalPrice = 360000,
                remainingPrice = 180000,
                scheduledDate = "Kamis 10 Juli 2025",
                scheduledTime = "14.00",
                status = BookingStatus.AKTIF
            )
        )
    }

    val filteredBookings = bookings.filter { it.status == selectedTab }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "List Pemesanan",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Primary)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tab Row
            TabSection(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Warning Message
            WarningMessage()

            Spacer(modifier = Modifier.height(16.dp))

            // Booking List
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(filteredBookings) { booking ->
                    BookingCard(booking = booking)
                }
            }
        }
    }
}

@Composable
fun TabSection(
    selectedTab: BookingStatus,
    onTabSelected: (BookingStatus) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TabButton(
            text = "Aktif",
            isSelected = selectedTab == BookingStatus.AKTIF,
            onClick = { onTabSelected(BookingStatus.AKTIF) },
            modifier = Modifier.weight(1f)
        )
        TabButton(
            text = "Selesai",
            isSelected = selectedTab == BookingStatus.SELESAI,
            onClick = { onTabSelected(BookingStatus.SELESAI) },
            modifier = Modifier.weight(1f)
        )
        TabButton(
            text = "Dibatalkan",
            isSelected = selectedTab == BookingStatus.DIBATALKAN,
            onClick = { onTabSelected(BookingStatus.DIBATALKAN) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(38.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Primary else PrimaryLight4,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WarningMessage() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, PrimaryLight),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = "Info",
                tint = PrimaryLight,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Melakukan pelunasan setelah jatuh tempo tanggal pelunasan, Maka DP Hangus",
                fontSize = 12.sp,
                color = Color(0xFF333333),
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun BookingCard(booking: BookingItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PrimaryLight),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // MUA Image
                Image(
                    painter = painterResource(id = booking.imageRes),
                    contentDescription = booking.muaName,
                    modifier = Modifier
                        .height(200.dp)
                        .width(100.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Booking Details
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = booking.muaName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Gray
                        )

                        // Status Badge
                        Surface(
                            color =  Color(color = 0xFF66CDAA),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "Aktif",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Price Info
                    Text(
                        text = "Rp ${String.format("%,d", booking.totalPrice).replace(',', '.')}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                    Text(
                        text = "Sisa Rp ${String.format("%,d", booking.remainingPrice).replace(',', '.')}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, PrimaryLight),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Info,
                                contentDescription = "Info",
                                tint = PrimaryLight,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Lakukan pelunasan Pada Hari Rabu, 9 Juli 2025",
                                fontSize = 12.sp,
                                color = Color(0xFF333333),
                                lineHeight = 16.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    // Schedule Date
                    Text(
                        text = "${booking.scheduledDate} - ${booking.scheduledTime}",
                        fontSize = 13.sp,
                        color = Gray
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { },
                            modifier = Modifier.width(80.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                "Batalkan",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            onClick = { },
                            modifier = Modifier.width(80.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                "Chat MUA",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Detail",
                            tint = Primary,
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    RiasinTheme {
        HistoryScreen()
    }
}