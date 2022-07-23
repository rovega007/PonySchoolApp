package com.example.ponyschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ponyschool.ui.Principal_Padre

class VerProgreso_Alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_progreso_alumno)

        val btnCan = findViewById<Button>(R.id.btnRegresar)
        btnCan.setOnClickListener {
            val intent = Intent(this, Principal_Alumno::class.java)
            startActivity(intent)
            finish()
        }
    }
}