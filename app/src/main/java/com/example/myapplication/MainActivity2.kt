package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    // Declaramos las variables que serán inicializadas en el initComponents más tarde.

    private lateinit var mostrarSalarioBruto: TextView
    private lateinit var mostrarSalarioNeto: TextView
    private lateinit var retencionIRPF: TextView
    private lateinit var grupo: TextView
    private lateinit var grado: TextView
    private lateinit var hijos: TextView
    private lateinit var btnRecalcular: Button

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


        // Declaramos las funciones.
        initComponents()
        initListener()
        initUI()





    }

    private fun initUI() {
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

    private fun initListener() {
        this.btnRecalcular.setOnClickListener{
            // Vuelve a la pantalla principal.
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initComponents() {

        // Recogemos la información en los textViews.
        this.mostrarSalarioBruto = findViewById(R.id.mostrarSalarioBruto)
        this.mostrarSalarioNeto = findViewById(R.id.mostrarSalarioNeto)
        this.retencionIRPF = findViewById(R.id.retencionIRPF)
        this.grupo = findViewById(R.id.deduccionesGrupo)
        this.grado = findViewById(R.id.deduccionesDisc)
        this.hijos = findViewById(R.id.deduccionesHijos)
        this.btnRecalcular = findViewById(R.id.ReCalcularNeto)
    }
}