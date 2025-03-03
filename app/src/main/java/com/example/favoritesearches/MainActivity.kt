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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit

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
    var editingIndex by remember { mutableIntStateOf(-1) }
    var editText by remember { mutableStateOf(TextFieldValue("")) }

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

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            tags.forEachIndexed { index, tag ->
                if (query.isEmpty() || tag.contains(query, ignoreCase = true)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
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
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                    ) {
                        if (editingIndex == index) {
                            BasicTextField(
                                value = editText,
                                onValueChange = { editText = it },
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Text(
                                text = tag,
                                color = Color(0xFF00796B),
                                modifier = Modifier.weight(1f).padding(8.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                if (editingIndex == index) {
                                    tags[index] = editText.text
                                    editingIndex = -1
                                } else {
                                    editText = TextFieldValue(tag)
                                    editingIndex = index
                                }
                            }
                        ) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }
                }
            }
        }
    }
}