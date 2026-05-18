package com.example.jan_aushadhifinder

data class Medicine(
    val brandName: String,
    val genericName: String,
    val brandPrice: Double,
    val genericPrice: Double,
    val dosage: String
)

val sampleMedicines = listOf(
    Medicine("Crocin", "Paracetamol", 30.0, 5.0, "500mg"),
    Medicine("Augmentin", "Amoxicillin + Clavulanic Acid", 200.0, 60.0, "625mg"),
    Medicine("Lipitor", "Atorvastatin", 150.0, 40.0, "10mg"),
    Medicine("Glycomet", "Metformin", 50.0, 12.0, "500mg"),
    Medicine("Pan-D", "Pantoprazole + Domperidone", 120.0, 35.0, "40mg/30mg"),
    Medicine("Telma-H", "Telmisartan + Hydrochlorothiazide", 180.0, 45.0, "40mg/12.5mg"),
    Medicine("Zyrtec", "Cetirizine", 40.0, 8.0, "10mg"),
    Medicine("Combiflam", "Ibuprofen + Paracetamol", 45.0, 10.0, "400mg/325mg"),
    Medicine("Azithral", "Azithromycin", 110.0, 30.0, "500mg"),
    Medicine("Voveran", "Diclofenac", 60.0, 15.0, "50mg")
)
