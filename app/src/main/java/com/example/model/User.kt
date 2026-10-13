package com.example.model

data class User(
    val name: String,
    val rut: String,
    val email: String,
    val role: String,
    val avatarInitial: String = name.firstOrNull()?.uppercase() ?: "U",
    val loginTime: String,
    val securityLevel: String = "Módulo 11 + Ticket Protegido",
    val cafeteriaName: String = "Casino Central • Comedor Principal",
    val lunchTicketNumber: String = "ALM-2026-849",
    val lunchStatus: String = "Almuerzo de Hoy Habilitado",
    val dietaryPlan: String = "Menú Balanceado Saludable",
    val remainingLuncheons: Int = 18
)

data class DemoAccount(
    val rut: String,
    val password: String,
    val name: String,
    val role: String,
    val email: String
)

object DemoAccountsRepository {
    val accounts = listOf(
        DemoAccount(
            rut = "12.345.678-5",
            password = "password123",
            name = "Juan Carlos Pérez Soto",
            role = "Beneficiario Almuerzo (Casino Central)",
            email = "juan.perez@almuerzoportal.cl"
        ),
        DemoAccount(
            rut = "19.876.543-0",
            password = "password123",
            name = "Valentina Paz Rojas Morales",
            role = "Supervisora de Casino & Nutrición",
            email = "v.rojas@almuerzoportal.cl"
        ),
        DemoAccount(
            rut = "15.000.005-K",
            password = "password123",
            name = "Camila Andrea Muñoz Bravo",
            role = "Estudiante • Beca de Almuerzo",
            email = "c.munoz@almuerzoportal.cl"
        ),
        DemoAccount(
            rut = "11.111.111-1",
            password = "password123",
            name = "Esteban Andrés Silva Castro",
            role = "Colaborador Comedor Institucional",
            email = "e.silva@almuerzoportal.cl"
        )
    )
}
