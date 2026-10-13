package com.example.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.DemoAccountsRepository
import com.example.ui.components.SecurityPlateChip
import com.example.ui.components.SecurityPlateLogo
import com.example.ui.components.SecurityPlateVerificationCard
import com.example.ui.theme.PastelCreamBackground
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
import com.example.viewmodel.LoginUiState
import com.example.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    uiState: LoginUiState,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    // Animación suave de respiración para que el plato se vea vivo y animado
    val infiniteTransition = rememberInfiniteTransition(label = "lunch_plate_anim")
    val plateScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(2400, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "plate_scale_anim"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        PastelCreamBackground,
                        PastelYellowContainer.copy(alpha = 0.6f),
                        PastelCreamBackground
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .widthIn(max = 540.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // Header Hero Banner - Dibujito animado del plato de comida
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp),
                shape = RoundedCornerShape(26.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // Imagen del plato en estilo dibujito animado con suave efecto de escala
                    Image(
                        painter = painterResource(id = R.drawable.img_lunch_dibujito),
                        contentDescription = "Dibujito animado de almuerzo feliz",
                        modifier = Modifier
                            .fillMaxSize()
                            .scale(plateScale),
                        contentScale = ContentScale.Crop
                    )

                    // Overlay sutil para destacar los textos sin tapar el dibujito
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x10000000),
                                        Color(0x75000000)
                                    )
                                )
                            )
                    )

                    // Logo de seguridad con plato de comida que verifica la app de almuerzo
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(14.dp)
                    ) {
                        SecurityPlateLogo(
                            size = 54.dp,
                            showBorder = true
                        )
                    }

                    // Textos descriptivos sobre el banner
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = PastelYellowContainer.copy(alpha = 0.95f),
                            modifier = Modifier.padding(bottom = 6.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.RestaurantMenu,
                                    contentDescription = null,
                                    tint = PastelOrangePrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Casino & Menú Diario de Almuerzo",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF78350F)
                                )
                            }
                        }

                        Text(
                            text = "Portal de Almuerzo RUT",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )

                        Text(
                            text = "Verifica tu ración y menú diario con tu RUT chileno",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFFEF3C7)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Food security badge banner
            SecurityPlateChip(
                text = "Seguridad Alimentaria: Consulta de Almuerzo RUT"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Beneficiarios de prueba
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = null,
                        tint = PastelOrangePrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Beneficiarios de prueba:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                    )
                }

                TextButton(
                    onClick = { viewModel.setDemoDialogVisible(true) },
                    modifier = Modifier.testTag("demo_accounts_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = "Ver Cuentas",
                        tint = PastelGreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Ver Cuentas Demo",
                        style = MaterialTheme.typography.labelMedium,
                        color = PastelGreenPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DemoAccountsRepository.accounts.take(2).forEach { demo ->
                    FilterChip(
                        selected = uiState.rut == demo.rut,
                        onClick = { viewModel.selectDemoAccount(demo) },
                        label = {
                            Text(
                                text = "${demo.name.split(" ").first()} (Almuerzo)",
                                style = MaterialTheme.typography.labelSmall
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = PastelYellowLight,
                            selectedLabelColor = Color(0xFF78350F)
                        ),
                        modifier = Modifier.testTag("quick_chip_${demo.rut}")
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Login Form Card en color pastel amarillo/naranja suave y alegre
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.5.dp,
                        color = PastelOrange.copy(alpha = 0.45f),
                        shape = RoundedCornerShape(24.dp)
                    ),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = PastelYellowContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Food header inside form
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(PastelOrangeLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Fastfood,
                                contentDescription = null,
                                tint = PastelOrangePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Ingreso al Menú de Hoy",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF78350F)
                            )
                            Text(
                                text = "Valida tu identidad con RUT para autorizar tu almuerzo",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }

                    // 1. Campo de RUT - En color amarillo/naranja pastel para que se vea más bonito y animado
                    OutlinedTextField(
                        value = uiState.rut,
                        onValueChange = { viewModel.onRutChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("rut_input"),
                        label = {
                            Text(
                                text = "RUT del Beneficiario",
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        placeholder = { Text("12.345.678-K") },
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(if (uiState.isRutValid) PastelGreenContainer else PastelOrangeLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Badge,
                                    contentDescription = "RUT",
                                    tint = if (uiState.isRutValid) PastelGreenPrimary else PastelOrangePrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        trailingIcon = {
                            if (uiState.isRutValid) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "RUT Válido",
                                    tint = ValidGreen,
                                    modifier = Modifier.size(22.dp)
                                )
                            } else if (uiState.rut.isNotEmpty()) {
                                IconButton(
                                    onClick = { viewModel.onRutChanged("") },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Limpiar RUT",
                                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        },
                        supportingText = {
                            if (uiState.rutError != null) {
                                Text(
                                    text = uiState.rutError,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            } else if (uiState.isRutValid) {
                                Text(
                                    text = "✓ RUT válido (Módulo 11 verificado • Almuerzo disponible)",
                                    color = ValidGreen,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            } else {
                                Text(
                                    text = "Ejemplo: 12.345.678-5 (con o sin puntos)",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.Black
                                )
                            }
                        },
                        isError = uiState.rutError != null,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Ascii,
                            imeAction = ImeAction.Next,
                            capitalization = KeyboardCapitalization.Characters
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFFBEB),   // Amarillo pastel cálido
                            unfocusedContainerColor = Color(0xFFFFF7ED), // Naranja pastel cálido suave
                            focusedBorderColor = PastelOrangePrimary,
                            unfocusedBorderColor = PastelOrange.copy(alpha = 0.5f),
                            focusedLabelColor = Color(0xFFC2410C),
                            unfocusedLabelColor = Color(0xFF78350F),
                            focusedTextColor = Color(0xFF292524),
                            unfocusedTextColor = Color(0xFF292524)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // 2. Campo de Contraseña - En color amarillo/naranja pastel
                    OutlinedTextField(
                        value = uiState.password,
                        onValueChange = { viewModel.onPasswordChanged(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("password_input"),
                        label = {
                            Text(
                                text = "Contraseña o Clave Casino",
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        placeholder = { Text("Ingresa tu contraseña") },
                        leadingIcon = {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(PastelOrangeLight),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "Contraseña",
                                    tint = PastelOrangePrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = { viewModel.togglePasswordVisibility() },
                                modifier = Modifier.testTag("toggle_password_visibility")
                            ) {
                                Icon(
                                    imageVector = if (uiState.isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = if (uiState.isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                                )
                            }
                        },
                        visualTransformation = if (uiState.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        supportingText = {
                            if (uiState.passwordError != null) {
                                Text(
                                    text = uiState.passwordError,
                                    color = MaterialTheme.colorScheme.error,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        },
                        isError = uiState.passwordError != null,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { viewModel.login() }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFFFFBEB),   // Amarillo pastel cálido
                            unfocusedContainerColor = Color(0xFFFFF7ED), // Naranja pastel cálido suave
                            focusedBorderColor = PastelOrangePrimary,
                            unfocusedBorderColor = PastelOrange.copy(alpha = 0.5f),
                            focusedLabelColor = Color(0xFFC2410C),
                            unfocusedLabelColor = Color(0xFF78350F),
                            focusedTextColor = Color(0xFF292524),
                            unfocusedTextColor = Color(0xFF292524)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    // 3. Recordar RUT & Olvidaste tu clave
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { viewModel.toggleRememberRut(!uiState.rememberRut) }
                                .padding(vertical = 4.dp, horizontal = 2.dp)
                        ) {
                            Checkbox(
                                checked = uiState.rememberRut,
                                onCheckedChange = { viewModel.toggleRememberRut(it) },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = ValidGreen
                                ),
                                modifier = Modifier.testTag("remember_rut_checkbox")
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Recordar RUT",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color(0xFF78350F)
                            )
                        }

                        TextButton(
                            onClick = { viewModel.setForgotPasswordDialogVisible(true) },
                            modifier = Modifier.testTag("forgot_password_button")
                        ) {
                            Text(
                                text = "¿Olvidaste tu clave?",
                                style = MaterialTheme.typography.labelMedium,
                                color = PastelOrangePrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Mensaje de error general si aplica
                    AnimatedVisibility(
                        visible = uiState.generalError != null,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colorScheme.errorContainer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = uiState.generalError ?: "",
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 4. Botón "Ver Mi Almuerzo" - EN COLOR VERDE solicitado por el usuario
                    Button(
                        onClick = { viewModel.login() },
                        enabled = !uiState.isLoading,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .testTag("login_button"),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ValidGreen, // Verde fresco y llamativo
                            contentColor = Color.White
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 3.dp,
                            pressedElevation = 6.dp
                        )
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.5.dp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Verificando Almuerzo...",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Restaurant,
                                contentDescription = null,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Ver Mi Almuerzo",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Trust & Security Card with Food Plate Security Logo
            SecurityPlateVerificationCard(
                rut = if (uiState.isRutValid) uiState.rut else null,
                statusText = "App oficial para verificar ración de almuerzo y menú semanal"
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Footer note
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Shield,
                    contentDescription = null,
                    tint = PastelGreenPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "Control Nutricional & Asignación de Almuerzos con RUT",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

    // Demo Accounts Dialog
    if (uiState.showDemoDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.setDemoDialogVisible(false) },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SecurityPlateLogo(size = 32.dp, showBorder = false)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Cuentas de Almuerzo Demo",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Selecciona un beneficiario de almuerzo para probar la aplicación:",
                        style = MaterialTheme.typography.bodySmall
                    )
                    DemoAccountsRepository.accounts.forEach { account ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { viewModel.selectDemoAccount(account) },
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = PastelYellowContainer
                            )
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = account.name,
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF78350F)
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(6.dp),
                                        color = PastelGreenContainer
                                    ) {
                                        Text(
                                            text = "ALMUERZO ACTIVO",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            color = PastelGreenPrimary,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "RUT: ${account.rut}",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = PastelOrangePrimary
                                )
                                Text(
                                    text = account.role,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { viewModel.setDemoDialogVisible(false) }) {
                    Text("Cerrar", color = ValidGreen, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    // Forgot Password Dialog
    if (uiState.showForgotPasswordDialog) {
        var rutForReset by remember { mutableStateOf(uiState.rut) }

        AlertDialog(
            onDismissRequest = { viewModel.setForgotPasswordDialogVisible(false) },
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Restaurant,
                        contentDescription = null,
                        tint = PastelOrangePrimary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Recuperar Clave de Casino")
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Ingresa tu RUT para enviarte instrucciones de recuperación de clave de acceso al casino:",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    OutlinedTextField(
                        value = rutForReset,
                        onValueChange = { rutForReset = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("reset_rut_input"),
                        label = { Text("RUT") },
                        placeholder = { Text("12.345.678-K") },
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    if (uiState.forgotPasswordStatusMessage != null) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PastelGreenContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = uiState.forgotPasswordStatusMessage,
                                color = PastelGreenPrimary,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.requestPasswordReset(rutForReset) },
                    colors = ButtonDefaults.buttonColors(containerColor = ValidGreen),
                    modifier = Modifier.testTag("submit_reset_password_button")
                ) {
                    Text("Enviar")
                }
            },
            dismissButton = {
                TextButton(onClick = { viewModel.setForgotPasswordDialogVisible(false) }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
