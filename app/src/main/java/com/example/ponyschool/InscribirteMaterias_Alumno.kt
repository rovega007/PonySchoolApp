package com.example.ponyschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InscribirteMaterias_Alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscribirte_materias_alumno)
        val v = findViewById<Button>(R.id.btnRegresar)
        v.setOnClickListener {
            val intent = Intent(this, Principal_Alumno::class.java)
            startActivity(intent)
            finish()
        }
    }
}