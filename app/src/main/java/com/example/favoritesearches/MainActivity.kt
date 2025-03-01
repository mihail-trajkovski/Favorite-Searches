package com.example.favoritesearches

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    var query by remember { mutableStateOf("") } // State for the search query input
    val tags = remember { mutableStateListOf<String>() } // State for the list of tags

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Title
        Text(
            text = "Favorite Twitter Searches",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Search Query Input
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter Twitter search query here") },
            modifier = Modifier.fillMaxWidth()
        )

        // Save Button
        Button(
            onClick = {
                if (query.isNotEmpty()) {
                    tags.add(query) // Add the query to the list of tags
                    query = "" // Clear the input field
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Save")
        }

        // Tagged Searches Title
        Text(
            text = "Tagged Searches",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        )

        // List of Tags
        Column {
            tags.forEach { tag ->
                Text(
                    text = tag,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}