package com.example.jan_aushadhifinder

import com.google.android.gms.maps.model.LatLng

data class Store(
    val name: String,
    val address: String,
    val location: LatLng,
    val isOpen: Boolean,
    val distance: String
)

val sampleStores = listOf(
    Store("Jan Aushadhi Kendra - Sector 1", "Shop 12, Main Market", LatLng(12.9716, 77.5946), true, "1.2 km"),
    Store("Jan Aushadhi Kendra - Health Center", "Near City Hospital", LatLng(12.9816, 77.6046), true, "2.5 km"),
    Store("Generic Med Store", "Opposite Railway Station", LatLng(12.9616, 77.5846), false, "3.8 km"),
    Store("PMBI Store - East", "East Wing Mall", LatLng(12.9916, 77.6146), true, "5.0 km")
)
