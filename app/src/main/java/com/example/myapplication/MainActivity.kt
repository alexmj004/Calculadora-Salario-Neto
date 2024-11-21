package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Especificamos lo que tiene que hacer la app al pulsar el botón.
        findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.loginButton)
            .setOnClickListener {
                // Todos los elementos a mostra en la siguiente página los guardamos en variables.
                val salarioBruto = findViewById<EditText>(R.id.salary);
                val pagas = findViewById<EditText>(R.id.pagas);
                val edad = findViewById<EditText>(R.id.edad);
                val grupo = findViewById<EditText>(R.id.grupo);
                val grado = findViewById<EditText>(R.id.grado);
                val estado = findViewById<EditText>(R.id.estado);
                val hijos = findViewById<EditText>(R.id.hijos);


                // Sí los datos están rellenados:
                if(salarioBruto.text.isNotEmpty() && pagas.text.isNotEmpty() && edad.text.isNotEmpty() && grupo.text.isNotEmpty() && grado.text.isNotEmpty() && estado.text.isNotEmpty() && hijos.text.isNotEmpty())

                    //Creamos un objeto de Intent, el cual nos permite gestionar el cambio de ventana
                    intent = Intent(this, MainActivity2::class.java)

                    // Salario bruto lo pasamos a un double.
                    val salaryBruto = salarioBruto.text.toString().toDouble()

                    // Retención IRPF:
                    val retencionIrpf = when {
                        salaryBruto <= 12450 -> 0.19
                        salaryBruto > 12450 && salaryBruto < 20200 -> 0.24
                        salaryBruto >= 20200 && salaryBruto < 35200 -> 0.30
                        salaryBruto >= 35200 && salaryBruto < 60000 -> 0.37
                        salaryBruto >= 60000 && salaryBruto < 300000 -> 0.45
                        else -> 0.47
                    }

                    // Por cada hijo deducimos 100€
                    val hijo = hijos.text.toString().toInt()
                    val deduccionHijos = hijo * 100

                    // Calcular las deducciones por grupo profesional
                    val grupoProfesional = grupo.text.toString().toInt()
                    val deduccionGrupo = when (grupoProfesional) {
                        1 -> 1847.40
                        2 -> 1532.10
                        3 -> 1332.90
                        in 4..6 -> 1323.00
                        else -> 0.0  // Sin deducción para otros grupos
                    }

                    // Calcular las deducciones por grado discapacidad
                    val gradoDiscapacidad = grado.text.toString().toDouble()
                    val deduccionGrado = when (gradoDiscapacidad) {
                        in 33.0..65.0 -> 3000.0
                        in 66.0..100.0 -> 9000.0
                        else -> 0.0

                    }


                    // Cálculo salario Neto anual.
                    val deduccionesTotales = deduccionHijos+deduccionGrupo+deduccionGrado
                    val calcSalarioNeto = salaryBruto-(salaryBruto*retencionIrpf)-deduccionesTotales



                    //Añadimos los valores o parametros que queremos pasar a la otra ventana
                    intent.putExtra("Salario Bruto Anual", salaryBruto)
                    intent.putExtra("Salario Neto Anual", calcSalarioNeto)
                    intent.putExtra("Retención IRPF", salaryBruto*retencionIrpf)
                    intent.putExtra("DEDUCCIONES GRUPO",deduccionGrupo )
                    intent.putExtra("DEDUCCIONES GRADO",deduccionGrado )
                    intent.putExtra("DEDUCCIONES HIJOS",deduccionHijos )

                    Log.i("LOGIN", salaryBruto.toString())
                    Log.i("LOGIN", calcSalarioNeto.toString())
                    Log.i("LOGIN", retencionIrpf.toString())
                    Log.i("LOGIN", deduccionGrupo.toString())
                    Log.i("LOGIN", deduccionGrado.toString())
                    Log.i("LOGIN", deduccionHijos.toString())


                    //Iniciamos la otra ventana
                    startActivity(intent)
            }






    }
}