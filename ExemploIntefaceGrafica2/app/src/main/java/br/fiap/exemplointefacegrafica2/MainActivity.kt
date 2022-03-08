package br.fiap.exemplointefacegrafica2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinnerTipoTelefone)
        val itensSpinner = arrayOf<String>("Selecione", "Residencial", "Comercial", "Celular", "Outro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itensSpinner)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val txtNome = findViewById<EditText>(R.id.txtNome)
        val txtTelefone = findViewById<EditText>(R.id.txtTelefone)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val cbTelefone = findViewById<CheckBox>(R.id.cbTelefone)
        val cbEmail = findViewById<CheckBox>(R.id.cbEmail)
        val btnMsg = findViewById<Button>(R.id.btnMsg)
        btnMsg.setOnClickListener{
            if (camposValidos()) {
                var msg = """Nome: ${txtNome.text}
                    |Telefone: ${txtTelefone.text}
                    |Tipo Telefone: ${spinner.selectedItem}
                    |E-mail: ${txtEmail.text}
                    |Preferências de Contato
                    """.trimMargin("|")
                if(cbTelefone.isChecked) {
                    msg += "\n - Telefone"
                }
                if(cbEmail.isChecked){
                    msg += "\n - E-mail"
                }
                alert("Boas Vindas", msg)
            }
        }

    }
    fun camposValidos() : Boolean {
        val txtNome = findViewById<EditText>(R.id.txtNome)
        val txtTelefone = findViewById<EditText>(R.id.txtTelefone)
        val spinner = findViewById<Spinner>(R.id.spinnerTipoTelefone)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)

        if (txtNome.text.trim().isEmpty()
            || txtTelefone.text.trim().isEmpty()
            || spinner.selectedItemPosition == 0
            || txtEmail.text.trim().isEmpty()) {
            Toast.makeText(this,"Preencha todos os campos", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun alert (titulo: String, mensagem: String){
        val builder = AlertDialog.Builder(this)
        builder
            .setTitle(titulo)
            .setMessage(mensagem)
            .setPositiveButton("OK", null)
        builder.create().show()
    }
}