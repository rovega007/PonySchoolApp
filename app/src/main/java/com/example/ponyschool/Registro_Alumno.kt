package com.example.ponyschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ponyschool.ui.Principal_Padre
import org.json.JSONObject

var idpadre:String=""
class Registro_Alumno : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_alumno)

        idpadre = intent.getSerializableExtra("idPadre") as String

        ////////////  Boton Cancelar
        val btnCan = findViewById<Button>(R.id.button11)
        btnCan.setOnClickListener {
            val intent = Intent(this, Principal_Padre::class.java)
            intent.putExtra("idPadre", idpadre)
            startActivity(intent)
            finish()
        }

        val btnAcep = findViewById<Button>(R.id.button12)
        btnAcep.setOnClickListener {
            checkdatos()
        }

    }

    private fun checkdatos() {

        var nombre = findViewById<EditText>(R.id.editTextTextPersonName2)
        var correo = findViewById<EditText>(R.id.editTextTextEmailAddress3)
        var contra = findViewById<EditText>(R.id.editTextTextPassword4)
        var ccontra = findViewById<EditText>(R.id.editTextTextPassword5)
        var curp = findViewById<EditText>(R.id.editTextTextEmailAddress4)
        var combobox = findViewById<Spinner>(R.id.spinner)
        var combobox1 = findViewById<Spinner>(R.id.spinner)
        var edad = findViewById<EditText>(R.id.editTextNumber2)
        var fe = findViewById<RadioButton>(R.id.radioButton3)
        var ma = findViewById<RadioButton>(R.id.radioButton4)

        var n = nombre.text.toString()
        var c = correo.text.toString()
        var p = contra.text.toString()
        var cp = ccontra.text.toString()
        var cur = curp.text.toString()
        var escolaridad = "";
        var grado = ""
        var e = edad.text.toString()
        var s = ""



        if (n.isEmpty() || c.isEmpty() || p.isEmpty() || cp.isEmpty() || cur.isEmpty() || (fe.isChecked && ma.isChecked) || (!fe.isChecked && !ma.isChecked)) {
            Toast.makeText(this, "RELLENE LOS CAMPOS REQUERIDOS", Toast.LENGTH_LONG).show()
        } else {
            if (fe.isChecked()) {
                s = "Mujer"
            } else {
                s = "Hombre"
            }

               if (combobox.selectedItemPosition == 0) {
                escolaridad = "Primaria"
                if (combobox1.selectedItemPosition == 0)
                    escolaridad = "1"
                else if (combobox1.selectedItemPosition == 1)
                    escolaridad = "2"
                else if (combobox1.selectedItemPosition == 2)
                    escolaridad = "3"
                else if (combobox1.selectedItemPosition == 3)
                    escolaridad = "4"
                else if (combobox1.selectedItemPosition == 4)
                    escolaridad = "5"
                else if (combobox1.selectedItemPosition == 5)
                    escolaridad = "6"

            } else if (combobox.selectedItemPosition == 1) {
                escolaridad = "Secundaria"
                if (combobox1.selectedItemPosition == 0)
                    grado = "1"
                else if (combobox1.selectedItemPosition == 1)
                    grado = "2"
                else if (combobox1.selectedItemPosition == 2)
                    grado = "3"
            }

              if (p.equals(cp)){
                    addHijo(n, c, p, cur, escolaridad, grado, e, s)
                     val intent = Intent(this, Principal_Padre::class.java)
                     intent.putExtra("idPadre", idpadre)
                     startActivity(intent)
                     finish()
                }



                else{
                   Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_LONG).show()
                   contra.text = null
                   ccontra.text = null
               }


        }


    }

    private fun addHijo(n: String,c: String,p: String,cur: String,escolaridad: String,grado: String,e: String,s: String){
        val url = "http://"+ip+":80/WSPonySchool/endPointRegistrarAlumno.php"
        val params = HashMap<String, String>()
        params.put("n", n)
        params.put("idp", idpadre)
        params.put("c", c)
        params.put("p", p)
        params.put("u", cur)
        params.put("ne", escolaridad)
        params.put("g", grado)
        params.put("e", e)
        params.put("s", s)
        params.put("r", "Alumno")
        params.put("f", "1")


        val request = object : StringRequest(
            Request.Method.POST, url,
            {
                println(    it)
                try {
                    val json = JSONObject(it)
                    println( it)
                    if(json.getInt("code") == 200) {

                        Toast.makeText(this,"REGISTRO EXITOSO", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, Principal_Padre::class.java)
                        intent.putExtra("idPadre",idpadre)
                        startActivity(intent)
                        finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this,"Error, Intenta mas tarde", Toast.LENGTH_LONG).show()
                }
            },
            {
                println(   "Error AQUI ERROR AQUI :" +it )
                Toast.makeText(this,"Problemas En El Servidor", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        Volley.newRequestQueue(this).add(request)
    }
}