package com.example.ponyschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ponyschool.ui.Principal_Padre

class RegistarBaja_Padre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar_baja_padre)
        val btnCan = findViewById<Button>(R.id.btnRegresar)
        btnCan.setOnClickListener {
            val intent = Intent(this, Principal_Padre::class.java)
            startActivity(intent)
            finish()
        }
    }
}