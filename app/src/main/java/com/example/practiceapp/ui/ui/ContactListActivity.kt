package com.example.practiceapp.ui.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practiceapp.data.ContactReader
import com.example.practiceapp.model.Contact
import com.example.practiceapp.model.InitialData.contactList
import com.example.practiceapp.ui.ui.component.ContactItem

class ContactListActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "Contact List", color = Color.White, fontWeight = FontWeight.Bold) },
                        backgroundColor = MaterialTheme.colorScheme.primary,
                    )
                },
                content = {
                    ContactListScreenPreview()
                },
            )
        }
    }
}

@Composable
fun ContactList(contacts: List<Contact>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize().padding(top = 64.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(contacts) { contact ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                ContactItem(contact = contact)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview() {
    val contacts = ContactReader.readContactsFromAssets(LocalContext.current)
    ContactList(contacts = contacts)
}
