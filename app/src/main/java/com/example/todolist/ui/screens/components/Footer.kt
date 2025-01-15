package com.example.todolist.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F9F9))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalDivider(
            Modifier
                .background(Color(0xFFDDDDDD))
                .height(1.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(16.dp))

        // Redes sociales
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Facebook,
                contentDescription = "Facebook",
                tint = Color(0xFF4267B2),
                modifier = Modifier.size(24.dp)
            )

        }

        Spacer(modifier = Modifier.size(16.dp))

        // Texto del footer
        Text(
            text = "RolApp: La app para gestionar juegos de rol",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF666666),
                textAlign = TextAlign.Center
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        // Copyright
        Text(
            text = "© 2025 RolApp. Todos los derechos reservados.",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFF999999),
                textAlign = TextAlign.Center
            )
        )
    }
}
