package com.example.practiceapp.ui.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.practiceapp.model.Contact

@Composable
fun ContactItem(contact: Contact) {
    Row(modifier = Modifier.padding(16.dp)) {
        AsyncImage(
            model = contact.avatar,
            contentDescription = "Avatar",
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Name: ${contact.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Address: ${contact.address}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Phone: ${contact.phoneNumber}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "DOB: ${contact.dob}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}