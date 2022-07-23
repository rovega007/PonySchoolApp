package com.example.ponyschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

var  ip:String="192.168.1.18"
class Registro_Padre : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_padre)

        val btnRe = findViewById<Button>(R.id.button4)
        btnRe.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val btnAdd = findViewById<Button>(R.id.button5)
        btnAdd.setOnClickListener {
            checkdatos()
        }

    }

    private fun checkdatos(){

        var nombre = findViewById<EditText>(R.id.editTextTextPersonName)
        var correo= findViewById<EditText>(R.id.editTextTextEmailAddress2)
        var contra= findViewById<EditText>(R.id.editTextTextPassword2)
        var ccontra= findViewById<EditText>(R.id.editTextTextPassword3)
        var edad=findViewById<EditText>(R.id.editTextNumber)
        var fe= findViewById<RadioButton>(R.id.radioButton)
        var ma= findViewById<RadioButton>(R.id.radioButton2)

        var n = nombre.text.toString()
        var c = correo.text.toString()
        var p = contra.text.toString()
        var cp = ccontra.text.toString()
        var e = edad.text.toString()
        var s=""



        if(n.isEmpty()||c.isEmpty()||p.isEmpty()||cp.isEmpty()||(fe.isChecked&&ma.isChecked)||(!fe.isChecked&&!ma.isChecked)){
            Toast.makeText(this,"RELLENE LOS CAMPOS REQUERIDOS", Toast.LENGTH_LONG).show()
        }
        else {
            if(fe.isChecked()) { s="Mujer" } else { s="Hombre" }
            if (p.equals(cp)){

                addpadre(n,c,p,e,s)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"La contraseña no coincide", Toast.LENGTH_LONG).show()
                contra.text =null
                ccontra.text =null
            }
        }

    }

    private fun addpadre(n: String,c: String,p: String,e: String,s: String){
        val url = "http://"+ip+":80/WSPonySchool/endPointRegistrarPadre.php"
        val params = HashMap<String, String>()
        params.put("n", n)
        params.put("c", c)
        params.put("p", p)
        params.put("e", e)
        params.put("s", s)
        params.put("r", "Padre")


        val request = object : StringRequest(
            Request.Method.POST, url,
            {
                try {
                    val json = JSONObject(it)
                    if(json.getInt("code") == 200) {

                        Toast.makeText(this,"Bienvenido", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
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