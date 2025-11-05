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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import com.frontend.riasin.ui.theme.RiasinTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMarisaScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPesanSekarang: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail MUA", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
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
        containerColor = BackgroundColor
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // MUA Profile Header
            MarisaProfileHeader(onPesanSekarang = onPesanSekarang)

            Spacer(modifier = Modifier.height(24.dp))

            // Portfolio Section
            MarisaPortfolioSection()

            Spacer(modifier = Modifier.height(24.dp))

            // About MUA
            MarisaAboutSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Service Packages
            MarisaServicePackagesSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Location
            MarisaLocationSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Availability Check
            MarisaAvailabilitySection()

            Spacer(modifier = Modifier.height(24.dp))

            // Reviews
            MarisaReviewsSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Verification Badge
            MarisaVerificationBadge()

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun MarisaProfileHeader(
    onPesanSekarang: () -> Unit
) {
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
                    .width(146.dp)
                    .height(149.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_marisa),
                    contentDescription = "Marisameimua",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
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
                    text = "Marisa Makeup",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp, top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryLight3),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Ikuti", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onPesanSekarang,
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Pesan Sekarang", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}


@Composable
fun MarisaPortfolioSection() {
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.makeup_wisuda2),
                contentDescription = "Portfolio 1",
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.makeup_pengantin),
                contentDescription = "Portfolio 2",
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
            )
        }
    }
}


@Composable
fun MarisaAboutSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Tentang MUA Ini",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "MUA dengan riasan natural & tahan lama, siap untuk wedding, wisuda, dan acara spesialmu.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun MarisaServicePackagesSection() {
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
        ) {
            Image(
                painter = painterResource(id = R.drawable.makeup_pengantin),
                contentDescription = "Portfolio 1",
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.makeup_wisuda),
                contentDescription = "Portfolio 2",
                modifier = Modifier
                    .weight(1f)
                    .height(200.dp)
            )
        }
    }
}

@Composable
fun MarisaLocationSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Lokasi & Jangkauan",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(
                text = "\uD83D\uDCCDDomisili: Surabaya \n",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Biaya tambahan dapat dikenakan untuk lokasi di luar jangkauan, belum termasuk biaya transport.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun MarisaAvailabilitySection() {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
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
            modifier = Modifier.width(180.dp).align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryLight3),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Lihat Jadwal", fontSize = 14.sp)
        }
    }
}

@Composable
fun MarisaReviewsSection() {
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
                Text("Lihat Semua", color = Color.Black, fontSize = 13.sp)
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Lihat Semua",
                    tint = Primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        MarisaReviewCard(
            userName = "Nadila Omara",
            comment = "Makeup-nya flawless! Tahan seharian makek, nggak retak-retak sama sekali. MUA-nya juga ramah banget!"
        )
    }
}

@Composable
fun MarisaReviewCard(userName: String, comment: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = userName,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "Client",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

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
                    text = "• Kemarin",
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
fun MarisaVerificationBadge() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Terverifikasi ✅",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryLight),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_verif),
                    contentDescription = "Verified",
                    modifier = Modifier.size(44.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "MUA ini telah diverifikasi oleh tim kami berdasarkan dokumen identitas & pengalaman kerja.",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailMarisaPreviewScreen() {
    RiasinTheme {
        DetailMarisaScreen {}
    }
}

