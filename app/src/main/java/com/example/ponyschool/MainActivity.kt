package com.example.ponyschool
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ponyschool.databinding.ActivityMainBinding
import com.example.ponyschool.ui.Principal_Padre
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    var  ip:String="192.168.1.18"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        /////////// Boton Registrar Padre

        val btnRe = findViewById<Button>(R.id.button2)
        btnRe.setOnClickListener {
            val intent = Intent(this, Registro_Padre::class.java)
            startActivity(intent)
            finish()
        }
        ////////////  Boton Aceptar
        val btnAce = findViewById<Button>(R.id.button)
        btnAce.setOnClickListener {
            var usuario = findViewById<EditText>(R.id.editTextTextEmailAddress)
            var contrasena = findViewById<EditText>(R.id.editTextTextPassword)
            var c = usuario.text.toString()
            var p = contrasena.text.toString()


            if (c.isEmpty() || p.isEmpty()) {

                Toast.makeText(this, "Porfavor, Ingresa tus datos", Toast.LENGTH_LONG).show()
                val mensaje = findViewById<TextView>(R.id.editTextTextEmailAddress)
                val mensaje1 = findViewById<TextView>(R.id.editTextTextPassword)
                mensaje.text = ""
                mensaje1.text = ""
            } else {
                Buscar(c, p)
            }

        } //Boton Aceptar

    }

    private fun Buscar(c: String, p: String) {

        val url = "http://"+ip+":80/WSPonySchool/endPointBuscarCorreoUsr.php"
        val params = HashMap<String, String>()
        params.put("u", c)
        params.put("p", p)
                val request = object : StringRequest(Request.Method.POST, url, {
                    try {
                        val json = JSONObject(it)
                        if(json.getInt("code") == 200) {
                            println( "Regreso un codigo 200")
                             interpretar(it)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this,"Error al Ingresar",Toast.LENGTH_LONG).show()
                    }
            },
            {
                println(   "Error AQUI ERROR AQUI :" +it )
                Toast.makeText(this,"Problemas En El Servidor",Toast.LENGTH_LONG).show()
            }
          ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        Volley.newRequestQueue(this).add(request)
    } //Metodo Buscar Usuario

    private fun interpretar(resultado: String) {
        try {
            val json = JSONObject(resultado)
            if(json.getInt("code") == 200 && json.has("resultados") && json.getJSONArray("resultados").length() > 0) {
                val array = json.getJSONArray("resultados")
                 var Rol=""
                var id=""
                for(i in 0..array.length()-1) {
                    val jObj = array.getJSONObject(i)
                     id=jObj.getString("idPadres").toString()
                     Rol =  jObj.getString("Rol").toString()

                }
                if(Rol.equals("Alumno")){
                    var intent = Intent(this, Principal_Alumno::class.java)
                    intent.putExtra("idAlumno",id)
                    startActivity(intent)
                    finish()
                }else if(Rol.equals("Padre")){
                    println(id)
                        var intent = Intent(this, Principal_Padre::class.java)
                        intent.putExtra("idPadre",id)
                        startActivity(intent)
                        finish()

                } else if(Rol.equals("Asesor")){
                    var intent = Intent(this, Principal_Asesor::class.java)
                    intent.putExtra("idAsesor",id)
                    startActivity(intent)
                    finish()

                }else if(Rol.equals("Mestro")){
                    var intent = Intent(this, Principal_Maestro::class.java)
                    intent.putExtra("idMaestro",id)
                    startActivity(intent)
                    finish()
                }


            } else {
                Toast.makeText(this, "DATOS ERRONEOS", Toast.LENGTH_LONG).show()
                val mensaje = findViewById<TextView>(R.id.editTextTextEmailAddress)
                val mensaje1 = findViewById<TextView>(R.id.editTextTextPassword)
                mensaje.text = ""
                mensaje1.text = ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this,"Error al interpretar", Toast.LENGTH_LONG).show()
        }
    }






} //Class