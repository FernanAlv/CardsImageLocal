package com.example.cardsimagelocal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ImageStyleCards()
            }
        }
    }
}

@Composable
fun ImageStyleCards() {
    val cardsData = listOf(
        CardData(
            imageRes = R.drawable.fernanfloo,
            username = "fernanfloo",
            description = "Holaaa chicos",
            avatarRes = R.drawable.fernanfloo_avatar
        ),
        CardData(
            imageRes = R.drawable.messi,
            username = "messi",
            description = "Simplemente Messi.",
            avatarRes = R.drawable.messi_avatar
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        items(cardsData) { card ->
            Cards(
                imageRes = card.imageRes,
                username = card.username,
                description = card.description,
                avatarRes = card.avatarRes
            )
        }
    }
}

data class CardData(
    val imageRes: Int,
    val username: String,
    val description: String,
    val avatarRes: Int
)

@Composable
fun Cards(imageRes: Int, username: String, description: String, avatarRes: Int) {
    val isLiked = remember { mutableStateOf(false) }


    val doubleClickEnabled = remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = avatarRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = username,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clickable {

                        isLiked.value = !isLiked.value
                    },
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isLiked.value) "❤️" else "🤍",
                        fontSize = 24.sp,
                        color = if (isLiked.value) Color.Red else Color.Black,
                        modifier = Modifier.clickable { isLiked.value = !isLiked.value }
                    )
                }

                Text(
                    text = username,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
