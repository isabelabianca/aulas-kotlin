package br.fiap.exemplo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_resultado)

    val lbResultado = findViewById<TextView>(R.id.lbResultado)
    lbResultado.text = intent.getStringExtra("msg")
  }
}