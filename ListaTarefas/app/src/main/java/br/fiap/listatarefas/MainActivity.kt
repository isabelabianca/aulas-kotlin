package br.fiap.listatarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var tarefas = ArrayList<String>()
    var adapter : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val lstTarefas = findViewById<ListView>(R.id.lstTarefas)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tarefas)
        lstTarefas.adapter = adapter

        fab.setOnClickListener {
            adicionarTarefa()
        }

        lstTarefas.setOnItemClickListener { parent, view, position, id ->
            val tarefa = parent.adapter.getItem(position).toString()
            atualizarTarefa(tarefa, position)

        }

    }

    fun atualizarTarefa(tarefa: String, posicao: Int) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Atualizar Tarefa");
        val entrada = EditText(this)
        entrada.setText(tarefa, TextView.BufferType.EDITABLE)
        builder.setView(entrada)
        //tratamento do botão OK
        builder.setPositiveButton("OK") { dialog, which ->
            tarefas[posicao] = entrada.text.toString()
            adapter?.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancel", null)
        //tratamento do botão excluir
        builder.setNeutralButton("Excluir") {dialog, which ->
            tarefas.removeAt(posicao)
            adapter?.notifyDataSetChanged()
        }
        builder.create().show()
    }

    fun adicionarTarefa() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nova Tarefa")
        val entrada = EditText(this)
        builder.setView(entrada)
        builder.setPositiveButton("Ok") { dialog, which ->
            tarefas.add(entrada.text.toString())
            adapter?.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancel", null)
        builder.create().show()
    }

}