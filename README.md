# 📱 App de Cálculo de Retenciones IRPF

## 🎯 Objetivo  
Crear una app Android que simule el cálculo de retenciones salariales basándose en datos personales del usuario.

## 🛠️ Requisitos Funcionales  

### 1. **Vista de Entrada de Datos**  
- Campos obligatorios:  
  ▸ Grupo profesional (dropdown)  
  ▸ Grado de discapacidad (slider/input)  
  ▸ Estado civil (radio buttons)  
  ▸ Número de hijos (number picker)  

### 2. **Vista de Resultados**  
- Datos a mostrar:  
  ```kotlin
  data class Resultados(
      val salarioBruto: Double,
      val salarioNeto: Double,
      val retencionIRPF: Double,
      val deducciones: Double
  )
