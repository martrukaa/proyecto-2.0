package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.PastelGreen
import com.example.ui.theme.PastelGreenContainer
import com.example.ui.theme.PastelGreenPrimary
import com.example.ui.theme.PastelOrange
import com.example.ui.theme.PastelOrangeLight
import com.example.ui.theme.PastelOrangePrimary
import com.example.ui.theme.PastelYellow
import com.example.ui.theme.PastelYellowContainer
import com.example.ui.theme.PastelYellowLight
import com.example.ui.theme.PastelYellowPrimary
import com.example.ui.theme.ValidGreen

/**
 * Insignia visual destacada del plato de comida dentro del escudo de seguridad,
 * diseñada para verificar que la aplicación es para consultar el almuerzo.
 */
@Composable
fun SecurityPlateLogo(
    modifier: Modifier = Modifier,
    size: Dp = 56.dp,
    showBorder: Boolean = true
) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(elevation = 3.dp, shape = CircleShape)
            .clip(CircleShape)
            .background(Color.White)
            .then(
                if (showBorder) {
                    Modifier.border(
                        width = 2.dp,
                        color = PastelGreenPrimary.copy(alpha = 0.5f),
                        shape = CircleShape
                    )
                } else Modifier
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_lunch_hero),
            contentDescription = "Logo: Plato de Almuerzo Animado",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

/**
 * Chip horizontal que combina el logo del plato de seguridad con texto informativo
 */
@Composable
fun SecurityPlateChip(
    modifier: Modifier = Modifier,
    text: String = "App de Almuerzo Verificada con RUT"
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = PastelYellowContainer,
        modifier = modifier.border(
            width = 1.dp,
            color = PastelOrange.copy(alpha = 0.4f),
            shape = RoundedCornerShape(50)
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SecurityPlateLogo(size = 24.dp, showBorder = false)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Verificado",
                tint = ValidGreen,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

/**
 * Tarjeta completa que enfatiza la autenticación segura para el servicio de almuerzo
 */
@Composable
fun SecurityPlateVerificationCard(
    modifier: Modifier = Modifier,
    rut: String? = null,
    statusText: String = "Acceso y Menú de Almuerzo Autorizado"
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = PastelYellowContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SecurityPlateLogo(size = 64.dp)

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Control de Almuerzo Seguro",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }

                Text(
                    text = if (rut != null) "RUT: $rut • $statusText" else statusText,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = PastelGreenContainer
                    ) {
                        Text(
                            text = "MÓDULO 11 OK",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = PastelGreenPrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = PastelOrangeLight
                    ) {
                        Text(
                            text = "TICKET ALMUERZO",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = PastelOrangePrimary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}
