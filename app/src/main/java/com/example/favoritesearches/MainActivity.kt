package com.example.favoritesearches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoriteSearchesApp()
        }
    }
}

@Composable
fun FavoriteSearchesApp() {
    var query by remember { mutableStateOf("") }
    val tags = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5))
    ) {

        Text(
            text = "Favorite Twitter Searches",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF00796B),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter Twitter search query here") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (query.isNotEmpty()) {
                    tags.add(query)
                    query = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Save", color = Color.White)
        }

        Button(
            onClick = {
                tags.clear()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00796B)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Clear Tags", color = Color.White)
        }

        Text(
            text = "Tagged Searches",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF00796B),
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )

        Column {
            tags.forEach { tag ->
                Box(
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF00796B),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            color = Color(0x2000796B),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = tag,
                        color = Color(0xFF00796B),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}