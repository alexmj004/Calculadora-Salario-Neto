package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Creamos variables privadas para recoger los elementos visuales, a través de inicialización tardía.
    // Con el lateinit les decimos que no la inicialice ahora, sí no mas tarde.

    private lateinit var salarioBruto: EditText
    private lateinit var pagas: EditText
    private lateinit var edad: EditText
    private lateinit var grupo: EditText
    private lateinit var grado: EditText
    private lateinit var estado: EditText
    private lateinit var hijos: EditText
    private lateinit var calcular: Button


    companion object{
        // Creamos las KEYS para la función navegable.
        const val BRUTO_KEY = "Salario Bruto Anual"
        const val NETO_KEY = "Salario Neto Anual"
        const val IRPF_KEY = "Retención IRPF"
        const val GRUPO_KEY = "DEDUCCIONES GRUPO"
        const val GRADO_KEY = "DEDUCCIONES GRADO"
        const val HIJOS_KEY = "DEDUCCIONES HIJOS"
    }

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


        // Funciones
        initComponents()
        initListeners()

    }



    private fun initListeners() {
        this.calcular.setOnClickListener{
            navigateToResult()
        }
    }


    private fun initComponents() {
        this.salarioBruto = findViewById(R.id.salary);
        this.pagas = findViewById(R.id.pagas);
        this.edad = findViewById(R.id.edad);
        this.grupo = findViewById(R.id.grupo);
        this.grado = findViewById(R.id.grado);
        this.estado = findViewById(R.id.estado);
        this.hijos = findViewById(R.id.hijos);
        this.calcular = findViewById(R.id.loginButton)
    }


    private fun navigateToResult(){


        if(salarioBruto.text.isNotEmpty() && pagas.text.isNotEmpty() && edad.text.isNotEmpty() && grupo.text.isNotEmpty() && grado.text.isNotEmpty() && estado.text.isNotEmpty() && hijos.text.isNotEmpty()){
            intent = Intent(this,MainActivity2::class.java)
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
            val deduccionHijos = hijo * 100.0

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
            intent.putExtra(BRUTO_KEY, salaryBruto)
            intent.putExtra(NETO_KEY, calcSalarioNeto)
            intent.putExtra(IRPF_KEY, salaryBruto*retencionIrpf)
            intent.putExtra(GRUPO_KEY,deduccionGrupo )
            intent.putExtra(GRADO_KEY,deduccionGrado )
            intent.putExtra(HIJOS_KEY,deduccionHijos )

            Log.i("LOGIN", salaryBruto.toString())
            Log.i("LOGIN", calcSalarioNeto.toString())
            Log.i("LOGIN", retencionIrpf.toString())
            Log.i("LOGIN", deduccionGrupo.toString())
            Log.i("LOGIN", deduccionGrado.toString())
            Log.i("LOGIN", deduccionHijos.toString())


            this.startActivity(intent)
        }

    }
}