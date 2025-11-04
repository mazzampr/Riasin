package com.frontend.riasin.ui.theme.pemesanan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
fun Pemesanan1Screen(
    muaName: String = "Laraz Makeup",
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onPilihLayanan: (String, String) -> Unit = { _, _ -> }
) {
    var selectedService by remember { mutableStateOf<String?>(null) }
    var numberOfPeople by remember { mutableStateOf(0) }
    var selectedLocation by remember { mutableStateOf<String?>(null) }
    var additionalNotes by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pemesanan", fontWeight = FontWeight.Bold) },
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
                .padding(16.dp)
        ) {
            // Title Section
            Text(
                text = "Pilih Layanan Makeup",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pilih paket makeup yang kamu butuhkan.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Service Options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ServiceCard(
                    title = "Makeup Pengantin",
                    price = "Rp3.500.000",
                    description = "Lihat Deskripsi Layanan",
                    imageRes = R.drawable.img_laraz,
                    isSelected = selectedService == "Pengantin",
                    onClick = { selectedService = "Pengantin" },
                    modifier = Modifier.weight(1f)
                )

                ServiceCard(
                    title = "Makeup Wisuda",
                    price = "Rp400.000",
                    description = "Lihat Deskripsi Layanan",
                    imageRes = R.drawable.img_laraz,
                    isSelected = selectedService == "Wisuda",
                    onClick = { selectedService = "Wisuda" },
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Number of People
            Text(
                text = "Jumlah Orang",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { if (numberOfPeople > 0) numberOfPeople-- },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PrimaryLight)
                ) {
                    Text("-", fontSize = 24.sp, color = Primary, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = numberOfPeople.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                IconButton(
                    onClick = { numberOfPeople++ },
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PrimaryLight)
                ) {
                    Text("+", fontSize = 24.sp, color = Primary, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Location Selection
            Text(
                text = "Lokasi Makeup",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            LocationOption(
                text = "Datang ke rumah",
                isSelected = selectedLocation == "rumah",
                onClick = { selectedLocation = "rumah" }
            )

            Spacer(modifier = Modifier.height(12.dp))

            LocationOption(
                text = "Datang ke studio",
                isSelected = selectedLocation == "studio",
                onClick = { selectedLocation = "studio" }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Additional Notes
            Text(
                text = "Catatan Tambahan",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Tulis permintaan khusus jika ada, seperti jenis kulit, warna outfit, atau alergi.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        lineHeight = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "Contoh: Kulit sensitif, warna dress peach, ingin hasil makeup natural.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Pilih Layanan Button
            Button(
                onClick = {
                    if (selectedService != null) {
                        onPilihLayanan(muaName, selectedService!!)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                enabled = selectedService != null && numberOfPeople > 0 && selectedLocation != null
            ) {
                Text(
                    text = "Pilih Layanan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun ServiceCard(
    title: String,
    price: String,
    description: String,
    imageRes: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) PrimaryLight2 else Color.White
        ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = if (isSelected) Primary else Color.LightGray
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Radio Button
            RadioButton(
                selected = isSelected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Primary,
                    unselectedColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = price,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun LocationOption(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Primary,
                unselectedColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Preview
@Composable
private fun Pemesanan1ScreenPreview() {
    RiasinTheme {
        Pemesanan1Screen()
    }
}

