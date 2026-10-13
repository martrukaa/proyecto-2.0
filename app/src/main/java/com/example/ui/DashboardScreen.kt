package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DinnerDining
import androidx.compose.material.icons.filled.Egg
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.LunchDining
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.User
import com.example.ui.components.SecurityPlateChip
import com.example.ui.components.SecurityPlateLogo
import com.example.ui.components.SecurityPlateVerificationCard
import com.example.ui.theme.PastelCreamBackground
import com.example.ui.theme.PastelGreen
import com.example.ui.theme.PastelGreenContainer
import com.example.ui.theme.PastelGreenLight
import com.example.ui.theme.PastelGreenPrimary
import com.example.ui.theme.PastelOrange
import com.example.ui.theme.PastelOrangeLight
import com.example.ui.theme.PastelOrangePrimary
import com.example.ui.theme.PastelSurfaceVariant
import com.example.ui.theme.PastelYellow
import com.example.ui.theme.PastelYellowContainer
import com.example.ui.theme.PastelYellowLight
import com.example.ui.theme.PastelYellowPrimary
import com.example.ui.theme.ValidGreen
import kotlinx.coroutines.launch

data class DayMenu(
    val dayName: String,
    val dateText: String,
    val mainCourse: String,
    val vegAlternative: String,
    val sideDish: String,
    val salad: String,
    val dessert: String,
    val beverage: String,
    val calories: Int,
    val protein: String,
    val carbs: String
)

val weeklyLunchMenus = listOf(
    DayMenu(
        dayName = "Hoy (Lun)",
        dateText = "Lunes 13 Octubre",
        mainCourse = "Pechuga de Pollo Dorada a las Finas Hierbas",
        vegAlternative = "Lentejas Estofadas con Verduras Salteadas y Choclo",
        sideDish = "Arroz Primavera con Choclo y Zanahorias",
        salad = "Mix Verde con Palta, Tomate Cherry y Pepino",
        dessert = "Fruta fresca de estación o Mousse de Maracuyá",
        beverage = "Limonada Natural con Menta fresca",
        calories = 560,
        protein = "38g",
        carbs = "52g"
    ),
    DayMenu(
        dayName = "Mar",
        dateText = "Martes 14 Octubre",
        mainCourse = "Pastel de Choclo Casero Tradicional",
        vegAlternative = "Pastel de Choclo Vegetariano con Pino de Champiñones",
        sideDish = "Ensalada Chilena clásica (Tomate y Cebolla)",
        salad = "Lechuga hidropónica con aderezo de sésamo",
        dessert = "Duraznos al Jugo naturales",
        beverage = "Jugo natural de Naranja y Zanahoria",
        calories = 620,
        protein = "32g",
        carbs = "68g"
    ),
    DayMenu(
        dayName = "Mié",
        dateText = "Miércoles 15 Octubre",
        mainCourse = "Filete de Merluza Austral al Horno con Papas Rústicas",
        vegAlternative = "Hamburguesa de Garbanzos y Quinoa con Palta",
        sideDish = "Papas doradas al romero y vegetales grillados",
        salad = "Rúcula, Tomates asados y Nueces",
        dessert = "Yogurt con Berries y Semillas de Chía",
        beverage = "Agua de Hierbas Frías (Cedrón y Limón)",
        calories = 510,
        protein = "35g",
        carbs = "46g"
    ),
    DayMenu(
        dayName = "Jue",
        dateText = "Jueves 16 Octubre",
        mainCourse = "Charquicán Campesino con Huevo Pochado",
        vegAlternative = "Charquicán de Verduras y Champiñones con Huevo de Campo",
        sideDish = "Betarragas cocidas en cubos con cilantro",
        salad = "Espinaca baby, Choclo tierno y Zanahoria rallada",
        dessert = "Leche Asada casera al caramelo",
        beverage = "Jugo natural de Manzana y Jengibre",
        calories = 540,
        protein = "28g",
        carbs = "58g"
    ),
    DayMenu(
        dayName = "Vie",
        dateText = "Viernes 17 Octubre",
        mainCourse = "Lasaña Bolognesa Gratinada",
        vegAlternative = "Lasaña de Espinacas, Ricotta y Zapallito Italiano",
        sideDish = "Pan de ajo horneado artesanal",
        salad = "Ensalada Caprese (Tomate, Mozzarella y Albahaca)",
        dessert = "Brownie de Cacao Saludable con Nueces",
        beverage = "Jugo de Frambuesa natural",
        calories = 650,
        protein = "31g",
        carbs = "72g"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    user: User,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler { onLogout() }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    var selectedDayIndex by remember { mutableIntStateOf(0) }
    var isVegetarianSelected by remember { mutableStateOf(false) }
    var isConfirmedAttendance by remember { mutableStateOf(false) }

    val currentMenu = weeklyLunchMenus[selectedDayIndex]

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SecurityPlateLogo(size = 36.dp, showBorder = false)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Portal Almuerzo RUT",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF78350F)
                            )
                            Text(
                                text = "Menú y Raciones de Casino",
                                style = MaterialTheme.typography.labelSmall,
                                color = PastelOrangePrimary
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = onLogout,
                        modifier = Modifier.testTag("logout_icon_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Cerrar sesión",
                            tint = PastelOrangePrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PastelYellowContainer
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            PastelCreamBackground,
                            PastelYellowContainer.copy(alpha = 0.4f),
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
                    .padding(16.dp)
                    .widthIn(max = 560.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                // 1. Security Verification Card with the Food Plate Logo
                // "el logo de seguridad tambien tenga un plato de comida para verificar que es una app para ver el almuerzo"
                SecurityPlateVerificationCard(
                    rut = user.rut,
                    statusText = "Almuerzo de Hoy Autorizado • Beneficio Activo"
                )

                // 2. Beneficiary Profile & Lunch Ticket Status
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Avatar with pastel yellow container
                            Box(
                                modifier = Modifier
                                    .size(56.dp)
                                    .clip(CircleShape)
                                    .background(PastelYellowPrimary),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = user.avatarInitial,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.width(14.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = user.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "RUT: ${user.rut}",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = PastelOrangePrimary
                                )
                                Text(
                                    text = user.role,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp),
                            color = PastelYellowContainer
                        )

                        // Lunch Ticket Summary Chips (Horario, Casino, Raciones)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            LunchStatusPill(
                                icon = Icons.Default.Schedule,
                                title = "Horario Casino",
                                value = "12:30 - 15:30",
                                color = PastelOrangeLight,
                                textColor = Color(0xFF7C2D12)
                            )
                            LunchStatusPill(
                                icon = Icons.Default.Restaurant,
                                title = "Comedor",
                                value = "Casino Central",
                                color = PastelYellowLight,
                                textColor = Color(0xFF78350F)
                            )
                            LunchStatusPill(
                                icon = Icons.Default.CheckCircle,
                                title = "Raciones Mes",
                                value = "${user.remainingLuncheons} disponibles",
                                color = PastelGreenContainer,
                                textColor = Color(0xFF14532D)
                            )
                        }
                    }
                }

                // 3. Weekly Day Selector Chips (Lunes a Viernes)
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Selecciona el día para ver el almuerzo:",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF78350F),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        weeklyLunchMenus.forEachIndexed { index, menu ->
                            val isSelected = selectedDayIndex == index
                            FilterChip(
                                selected = isSelected,
                                onClick = { selectedDayIndex = index },
                                label = {
                                    Text(
                                        text = menu.dayName,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = PastelOrangePrimary,
                                    selectedLabelColor = Color.White,
                                    containerColor = PastelYellowContainer
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("day_chip_$index")
                            )
                        }
                    }
                }

                // 4. Main Lunch Menu Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Title row with Day and Meal type
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(PastelOrangeLight),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LunchDining,
                                        contentDescription = null,
                                        tint = PastelOrangePrimary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = "Almuerzo: ${currentMenu.dateText}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.ExtraBold,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Cocinado fresco hoy • 100% balanceado",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = PastelGreenPrimary,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }

                        // Vegetarian Alternative Switch Toggle
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isVegetarianSelected) PastelGreenContainer else PastelYellowContainer,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Egg,
                                        contentDescription = null,
                                        tint = if (isVegetarianSelected) PastelGreenPrimary else Color(0xFF78350F),
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = if (isVegetarianSelected) "Opción Vegetariana Activada" else "Cambiar a Opción Vegetariana",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isVegetarianSelected) Color(0xFF14532D) else Color(0xFF78350F)
                                    )
                                }

                                Switch(
                                    checked = isVegetarianSelected,
                                    onCheckedChange = { isVegetarianSelected = it },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = PastelGreenPrimary,
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = PastelYellow
                                    ),
                                    modifier = Modifier.testTag("vegetarian_toggle")
                                )
                            }
                        }

                        // Dish detail section
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Plato de Fondo
                            MealItemRow(
                                label = "Plato de Fondo",
                                value = if (isVegetarianSelected) currentMenu.vegAlternative else currentMenu.mainCourse,
                                icon = Icons.Default.DinnerDining,
                                iconTint = PastelOrangePrimary,
                                highlight = true
                            )

                            // Acompañamiento
                            MealItemRow(
                                label = "Acompañamiento",
                                value = currentMenu.sideDish,
                                icon = Icons.Default.Restaurant,
                                iconTint = PastelYellowPrimary
                            )

                            // Ensalada
                            MealItemRow(
                                label = "Ensalada del Día",
                                value = currentMenu.salad,
                                icon = Icons.Default.FitnessCenter,
                                iconTint = PastelGreenPrimary
                            )

                            // Postre
                            MealItemRow(
                                label = "Postre Casero",
                                value = currentMenu.dessert,
                                icon = Icons.Default.Star,
                                iconTint = PastelOrange
                            )

                            // Bebida / Jugo
                            MealItemRow(
                                label = "Bebida / Jugo",
                                value = currentMenu.beverage,
                                icon = Icons.Default.LocalDrink,
                                iconTint = Color(0xFF0284C7)
                            )
                        }

                        HorizontalDivider(color = PastelSurfaceVariant)

                        // Nutritional Breakdown Cards (Calorías, Proteínas, Carbohidratos)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            NutritionalPill(
                                label = "Energía",
                                value = "${currentMenu.calories} kcal",
                                color = PastelOrangeLight
                            )
                            NutritionalPill(
                                label = "Proteína",
                                value = currentMenu.protein,
                                color = PastelGreenContainer
                            )
                            NutritionalPill(
                                label = "Carbohidratos",
                                value = currentMenu.carbs,
                                color = PastelYellowLight
                            )
                        }
                    }
                }

                // 5. Action: Confirm Attendance for Lunch
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isConfirmedAttendance) PastelGreenContainer else PastelOrangeLight
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = if (isConfirmedAttendance) Icons.Default.CheckCircle else Icons.Default.QrCode2,
                                contentDescription = null,
                                tint = if (isConfirmedAttendance) PastelGreenPrimary else PastelOrangePrimary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = if (isConfirmedAttendance) "¡Asistencia a Almuerzo Confirmada!" else "Confirma tu Asistencia al Casino",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isConfirmedAttendance) Color(0xFF14532D) else Color(0xFF7C2D12)
                                )
                                Text(
                                    text = if (isConfirmedAttendance) "Tu ración está reservada con RUT ${user.rut} en Casino Central" else "Evita filas confirmando que almorzarás hoy",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (isConfirmedAttendance) Color(0xFF166534) else Color(0xFF9A3412)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Button(
                            onClick = {
                                isConfirmedAttendance = !isConfirmedAttendance
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        if (isConfirmedAttendance) "¡Ración de almuerzo confirmada con éxito!" else "Reserva cancelada."
                                    )
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isConfirmedAttendance) PastelGreenPrimary else PastelOrangePrimary,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("confirm_lunch_button")
                        ) {
                            Icon(
                                imageVector = if (isConfirmedAttendance) Icons.Default.Check else Icons.Default.Restaurant,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isConfirmedAttendance) "Almuerzo Confirmado ✓" else "Confirmar Almuerzo de Hoy",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // 6. Security Protocol Detail with Food Plate Logo
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PastelYellowLight, RoundedCornerShape(16.dp))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SecurityPlateLogo(size = 44.dp, showBorder = false)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Garantía de Alimentación & Seguridad",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF78350F)
                            )
                            Text(
                                text = "Validación criptográfica de RUT (Módulo 11) para control higiénico y asignación única de raciones por persona.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LunchStatusPill(
    icon: ImageVector,
    title: String,
    value: String,
    color: Color,
    textColor: Color
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = color,
        modifier = Modifier.padding(horizontal = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = textColor.copy(alpha = 0.8f)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }
    }
}

@Composable
private fun MealItemRow(
    label: String,
    value: String,
    icon: ImageVector,
    iconTint: Color,
    highlight: Boolean = false
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = if (highlight) PastelYellowContainer else Color.Transparent,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = if (highlight) 8.dp else 4.dp, horizontal = if (highlight) 8.dp else 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(iconTint.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (highlight) FontWeight.Bold else FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun NutritionalPill(
    label: String,
    value: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = color,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
