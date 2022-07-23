package com.example.ponyschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Principal_Alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_alumno)

        val btnMa = findViewById<Button>(R.id.btnInsMat)
        btnMa.setOnClickListener {
            val intent = Intent(this, InscribirteMaterias_Alumno::class.java)
            startActivity(intent)

        }

        val verma = findViewById<Button>(R.id.btnVerMaterias)
        verma.setOnClickListener {
            val intent = Intent(this, VerMaterias_Alumno::class.java)
            startActivity(intent)

        }
        val v = findViewById<Button>(R.id.btnVerContenido)
        v.setOnClickListener {
            val intent = Intent(this, Contenido_Alumno::class.java)
            startActivity(intent)

        }

        val b = findViewById<Button>(R.id.btnVerProgreso)
        b.setOnClickListener {
            val intent = Intent(this, VerProgreso_Alumno::class.java)
            startActivity(intent)

        }

        val c = findViewById<Button>(R.id.button15)
        c.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}