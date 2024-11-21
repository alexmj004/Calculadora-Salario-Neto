package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recogemos la información en los textViews.
        val mostrarSalarioBruto = findViewById<TextView>(R.id.mostrarSalarioBruto)
        val mostrarSalarioNeto = findViewById<TextView>(R.id.mostrarSalarioNeto)
        val retencionIRPF = findViewById<TextView>(R.id.retencionIRPF)
        val grupo = findViewById<TextView>(R.id.deduccionesGrupo)
        val grado = findViewById<TextView>(R.id.deduccionesDisc)
        val hijos = findViewById<TextView>(R.id.deduccionesHijos)


        //Mediante el intent accedemos a los extras y los recogemos.
        //Tenemos que manejar la posibilidad que sea null.
        val salarioBruto: Double = intent.extras?.getDouble("Salario Bruto Anual")!!
        val salarioNeto: Double = intent.extras?.getDouble("Salario Neto Anual")!!
        val irpf: Double = intent.extras?.getDouble("Retención IRPF")!!
        val deduccGrado: Double = intent.extras?.getDouble("DEDUCCIONES GRUPO")!!
        val duccDisc: Double = intent.extras?.getDouble("DEDUCCIONES GRADO")!!
        val deduccHijos: Double = intent.extras?.getDouble("DEDUCCIONES HIJOS")!!


        //Modificamos el texto del resultTV
        mostrarSalarioBruto.text = "Salario Bruto Anual = $salarioBruto €"
        mostrarSalarioNeto.text = "Salario Neto Anual = $salarioNeto €"
        retencionIRPF.text = "Retención IRPF Anual = $irpf €"
        grupo.text = "Deduccion grupo profesional = $deduccGrado €"
        grado.text = "Deduccion grado discapacidad = $duccDisc €"
        hijos.text = "Deduccion número de hijos = $deduccHijos €"



    }
}