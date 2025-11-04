package com.frontend.riasin.ui.theme.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
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

// Data classes for MUA
data class MUA(
    val id: String,
    val name: String,
    val rating: Float,
    val imagePlaceholder: Int = 0,
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onMUAClick: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Header with profile and icons
        HeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        SearchBar()

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.banner_home),
            contentDescription = "Banner Home",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Categories
        CategoriesSection()

        Spacer(modifier = Modifier.height(24.dp))

        // Recommendations
        RecommendationsSection(onMUAClick = onMUAClick)

        Spacer(modifier = Modifier.height(24.dp))

        // Followed MUA
        FollowedMUASection(onMUAClick = onMUAClick)

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Icon
        Image(
            painter = painterResource(id = R.drawable.user_logo),
            contentDescription = "Profile",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )

        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo_riasin),
            contentDescription = "Riasin Logo",
            modifier = Modifier
                .width(150.dp)
                .height(88.dp)
        )

        // Notification Icons
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(
                painter = painterResource(com.frontend.riasin.R.drawable.ic_notif),
                contentDescription = "Notifications",
                tint = Primary,
                modifier = Modifier.size(24.dp)
            )
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorites",
                tint = Primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Cari nama MUA atau lokasi...", color = Color.Gray) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Primary
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = PrimaryLight2,
            unfocusedContainerColor = PrimaryLight2,
            disabledContainerColor = PrimaryLight2,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Primary,
        ),
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun CategoriesSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Kategori Makeup",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Menu Lainnya",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "More Categories",
                    tint = Primary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CategoryItem("Wisuda", R.drawable.ic_wisuda)
            CategoryItem("Pernikahan", R.drawable.ic_pernikahan)
            CategoryItem("Tunangan", R.drawable.ic_tunangan)
            CategoryItem("Reguler", R.drawable.ic_reguler)
        }
    }
}

@Composable
fun CategoryItem(
    name: String,
    logotRes: Int = R.drawable.ic_pernikahan
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(PrimaryLight),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(4.dp)
            ) {
                Image(
                    painter = painterResource(id = logotRes),
                    contentDescription = name,
                    modifier = Modifier.size(42.dp)
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun RecommendationsSection(onMUAClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "Rekomendasi MUA untuk Kamu",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        val recommendations = listOf(
            MUA("1", "Laraz Makeup", 4.8f, R.drawable.img_laraz),
            MUA("2", "Naja_Wedding", 4.8f, R.drawable.img_naja),
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recommendations) { mua ->
                MUACard(mua = mua, onMUAClick = onMUAClick, imageRes = mua.imagePlaceholder)
            }
        }
    }
}

@Composable
fun FollowedMUASection(onMUAClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = "MUA yang kamu ikuti",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        val followedMUA = MUA("3", "Marisameldimua", 4.8f, R.drawable.img_marisa)
        MUACard(mua = followedMUA, onMUAClick = onMUAClick, isWide = true, imageRes = followedMUA.imagePlaceholder)
    }
}

@Composable
fun MUACard(
    mua: MUA,
    onMUAClick: (String) -> Unit,
    isWide: Boolean = false,
    imageRes: Int = R.drawable.img_laraz
) {
    Card(
        modifier = Modifier
            .width(if (isWide) 180.dp else 160.dp)
            .clickable { onMUAClick(mua.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryLight),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            // Image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = mua.name,
                    modifier = Modifier
                        .fillMaxSize()
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

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = mua.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = Primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = mua.rating.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { onMUAClick(mua.id) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9AA8)),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 5.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Lihat Detail", fontSize = 12.sp)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    RiasinTheme {
        HomeScreen()
    }
}

@Preview
@Composable
private fun CardMuaPreview() {
    RiasinTheme { 
        MUACard(
            mua = MUA(
                id = "1",
                name = "Laraz Makeup",
                rating = 4.8f
            ),
            onMUAClick = {}
        )
    }
}