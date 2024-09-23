package com.example.winky_app

fun main() {
    val angkaSatuSepuluh: IntRange = 1..10
    println("Ada angka 5 kah antara 1-10? : ${5 in angkaSatuSepuluh}")
    println("Ada angka 11 kah antara 1-10? : ${11 in angkaSatuSepuluh}")

    val hurufAZ: CharRange = 'A'..'Z'
    println("Ada huruf R kah antara A-Z? : ${'R' in hurufAZ}")
    println("Ada angka ü kah antara A-Z? : ${'ü' in hurufAZ}")

    for (angka: Int in 1..5) {
        print(" " + angka)
    }
    println()

    val satuSampeLima: IntRange = 1.rangeTo(5)
    print("Rangenya 1-5 : ")
    for (x: Int in satuSampeLima) {
        print(" " + x)
    }
    println()

    val tujuhKeDua: IntProgression = 7.downTo(2)
    print("Rangenya 7-2 : ")
    for (y: Int in tujuhKeDua) {
        print(" " + y)
    }
}