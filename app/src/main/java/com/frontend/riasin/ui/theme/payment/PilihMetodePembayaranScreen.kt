package com.frontend.riasin.ui.theme.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frontend.riasin.ui.theme.PrimaryLight
import com.frontend.riasin.ui.theme.RiasinTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PilihMetodePembayaranScreen(
    onBackClick: () -> Unit = {},
    onQrisClick: () -> Unit = {},
    onVirtualAccountClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pilih Metode Pembayaran",
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
        containerColor = Color(0xFFFAFAFA)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // QRIS Payment Option
            PaymentMethodCard(
                title = "QRIS",
                subtitle = "Biaya Admin: Rp0",
                onClick = onQrisClick
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Virtual Account Payment Option
            PaymentMethodCard(
                title = "Virtual Account",
                subtitle = "Biaya Admin: Rp4.000",
                onClick = onVirtualAccountClick
            )
        }
    }
}

@Composable
fun PaymentMethodCard(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        color = Color(0xFFFDE7ED),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon container
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(PrimaryLight),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = when (title.lowercase(Locale.ROOT)) {
                        "qris" -> com.frontend.riasin.R.drawable.ic_qris
                        "virtual account" -> com.frontend.riasin.R.drawable.ic_va
                        else -> com.frontend.riasin.R.drawable.ic_va
                    }),
                    modifier = modifier.padding(8.dp),
                    contentDescription = null,
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
private fun PilihMetodePembayaranScreenPreview() {
    RiasinTheme {
        PilihMetodePembayaranScreen()
    }
}

