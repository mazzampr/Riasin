package com.frontend.riasin.ui.theme.pemesanan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frontend.riasin.ui.theme.Primary
import com.frontend.riasin.ui.theme.PrimaryLight
import com.frontend.riasin.ui.theme.RiasinTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    muaName: String = "Laraz Makeup",
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onLanjut: (String) -> Unit = {}
) {
    var selectedAddress by remember { mutableStateOf<String?>("Rumah") }
    var addNewAddress by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Booking", fontWeight = FontWeight.Bold) },
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
                text = "Gunakan alamat yang sudah pernah kamu simpan sebelumnya:",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Saved Address Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = PrimaryLight),
                shape = RoundedCornerShape(12.dp),
                onClick = { /* Navigate to address details */ }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            tint = Primary,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "Rumah",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }

                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Details",
                        tint = Primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add New Address Section
            Text(
                text = "Belum ada alamat tersimpan?",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = addNewAddress,
                    onCheckedChange = { addNewAddress = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Primary,
                        uncheckedColor = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Masukkan alamat baru untuk melanjutkan pemesanan.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Lanjut Button
            Button(
                onClick = { onLanjut(muaName) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(12.dp),
                enabled = selectedAddress != null || addNewAddress
            ) {
                Text(
                    text = "Lanjut",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
private fun BookingScreenPreview() {
    RiasinTheme {
        BookingScreen()
    }
}

