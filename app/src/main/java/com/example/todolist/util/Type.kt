package com.example.todolist.util

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.todolist.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    // Letra normal para el texto
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.antiqua)),
        fontWeight = FontWeight.SemiBold, // Peso ligeramente más grueso para destacar
        fontSize = 18.sp, // Tamaño mediano para lectura cómoda
        lineHeight = 26.sp, // Altura de línea más espaciosa para claridad
        letterSpacing = 0.25.sp, // Separación ligera entre letras
        color = MedievalColours.LeatherAged
    ),

    // Letra para títulos pequeños
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.medievalsharp)),
        fontWeight = FontWeight.Bold, // Peso más grueso para resaltar
        fontSize = 20.sp, // Un poco más grande que bodyMedium
        lineHeight = 28.sp, // Altura de línea proporcionalmente mayor
        letterSpacing = 0.15.sp,
        color = MedievalColours.LeatherAged
),

    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.cincel)),
        fontWeight = FontWeight.Bold, // Peso más grueso para resaltar
        fontSize = 25.sp, // Un poco más grande que bodyMedium
        lineHeight = 28.sp, // Altura de línea proporcionalmente mayor
        letterSpacing = 0.15.sp,
        color = MedievalColours.LeatherAged
),


)