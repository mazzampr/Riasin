package com.frontend.riasin.ui.theme.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
fun DetailLarazScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPesanSekarang: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail MUA", fontWeight = FontWeight.Bold) },
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
            // MUA Profile Header
            LarazProfileHeader()

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons
            LarazActionButtons(onPesanSekarang = onPesanSekarang)

            Spacer(modifier = Modifier.height(24.dp))

            // Portfolio Section
            LarazPortfolioSection()

            Spacer(modifier = Modifier.height(24.dp))

            // About MUA
            LarazAboutSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Service Packages
            LarazServicePackagesSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Location
            LarazLocationSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Availability Check
            LarazAvailabilitySection()

            Spacer(modifier = Modifier.height(24.dp))

            // Reviews
            LarazReviewsSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Verification Badge
            LarazVerificationBadge()

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun LarazProfileHeader() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image Placeholder
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_laraz),
                    contentDescription = "Laraz Makeup",
                    modifier = Modifier.fillMaxSize()
                )

                // Favorite icon
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        tint = PrimaryLight,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Laraz Makeup",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "4.8",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun LarazActionButtons(onPesanSekarang: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = {},
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text("Ikuti", fontSize = 14.sp)
        }

        Button(
            onClick = onPesanSekarang,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Pesan Sekarang", color = Primary, fontSize = 14.sp)
        }
    }
}

@Composable
fun LarazPortfolioSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Portofolio Makeup",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LarazPortfolioCategory("Makeup Pengantin", Modifier.weight(1f))
            LarazPortfolioCategory("Makeup Wisuda", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Portfolio Grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(400.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(4) { index ->
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PrimaryLight2),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Portfolio $index",
                        tint = Primary,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LarazPortfolioCategory(title: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, Primary)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = Primary
            )
        }
    }
}

@Composable
fun LarazAboutSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Tentang MUA Ini",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Berpengalaman sejak 2018, spesialis dalam makeup wisuda dan pengantin. Mengutamakan tampilan natural dan tahan lama.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun LarazServicePackagesSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Paket Layanan",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        LarazServicePackageCard("Makeup Pengantin", "Mulai dari Rp3.000.000", "Lihat Selengkapnya")
        Spacer(modifier = Modifier.height(12.dp))
        LarazServicePackageCard("Makeup Wisuda", "Mulai dari Rp250.000", "Lihat Selengkapnya")
    }
}

@Composable
fun LarazServicePackageCard(type: String, price: String, details: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PrimaryLight2),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = type,
                    tint = Primary,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = type,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = price,
                    style = MaterialTheme.typography.bodySmall,
                    color = Primary,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = details,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@Composable
fun LarazLocationSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Lokasi & Jangkauan",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Primary,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Gunungsari, Surabaya",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "Tersedia di area studio.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Biaya tambahan dapat dikenakan untuk lokasi di luar jam tertentu, belum termasuk biaya transport.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
fun LarazAvailabilitySection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Cek Ketersediaan Jadwal",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Klik lihat jadwal untuk melihat tanggal dan jam tersedia.",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Lihat Jadwal", fontSize = 14.sp)
        }
    }
}

@Composable
fun LarazReviewsSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ulasan Pelanggan",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextButton(onClick = {}) {
                Text("Lihat Semua", color = Primary, fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LarazReviewCard(
            userName = "Nadila Omara",
            rating = 5f,
            comment = "Makeup-nya flawless! Tahan seharian makek, nggak retak-retak sama sekali. MUA-nya juga ramah banget!",
            date = "2 hari lalu"
        )
    }
}

@Composable
fun LarazReviewCard(userName: String, rating: Float, comment: String, date: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryLight)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xFFFFB800),
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "5 komentar",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = comment,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun LarazVerificationBadge() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Verified",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(32.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "Terverifikasi ✓",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4CAF50)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "MUA ini telah diverifikasi oleh tim kami dan memiliki identitas & pengalaman kerja.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailLarazPreviewScreen() {
    RiasinTheme {
        DetailLarazScreen {}
    }
}
