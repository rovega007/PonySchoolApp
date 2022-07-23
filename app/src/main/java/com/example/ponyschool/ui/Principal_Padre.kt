package com.example.ponyschool.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ponyschool.*

var idpadre:String=""

class Principal_Padre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal_padre)

        idpadre=intent.getSerializableExtra("idPadre") as String
        println(idpadre)
        ////////////  Boton Cancelar
        val btnCan = findViewById<Button>(R.id.button10)
        btnCan.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        ////////////  Boton Hijo
        val btnRH = findViewById<Button>(R.id.button6)
        btnRH.setOnClickListener {
            val intent = Intent(this, Registro_Alumno::class.java)
            intent.putExtra("idPadre",idpadre)
            startActivity(intent)
            finish()
        }

        val vp = findViewById<Button>(R.id.button7)
        vp.setOnClickListener {
            val intent = Intent(this, VerProgreso_Padre::class.java)
            intent.putExtra("idPadre",idpadre)
            startActivity(intent)
            finish()
        }
        val btned = findViewById<Button>(R.id.button9)
        btned.setOnClickListener {
            val intent = Intent(this, EditarDatos_Padre::class.java)

            startActivity(intent)
            finish()
        }

        val btnb = findViewById<Button>(R.id.button10)
        btnb.setOnClickListener {
            val intent = Intent(this, RegistarBaja_Padre::class.java)

            startActivity(intent)
            finish()
        }

    }


}