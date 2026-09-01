package com.example.tarefasapi.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tarefasapi.R

/**
 * CreateTaskActivity
 *
 * Permite ao utilizador criar uma nova tarefa.
 *
 * Nesta primeira etapa é feita a preparação do ecrã
 * e a validação dos dados introduzidos.
 *
 * Posteriormente será ligada ao ViewModel e Repository
 * para enviar a tarefa através de uma API REST.
 */
class CreateTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga esta Activity ao respetivo layout XML.
        setContentView(R.layout.activity_create_task)

        // Referência ao campo onde será introduzido o título.
        val edtTaskTitle =
            findViewById<EditText>(R.id.edtTaskTitle)

        // Referência ao botão Guardar.
        val btnSaveTask =
            findViewById<Button>(R.id.btnSaveTask)

        /**
         * Evento executado quando o utilizador
         * clica no botão Guardar.
         */
        btnSaveTask.setOnClickListener {

            // Obtém o título introduzido.
            val title = edtTaskTitle.text.toString().trim()

            /**
             * Validação obrigatória.
             *
             * Uma tarefa não pode ser criada sem título.
             */
            if (title.isEmpty()) {

                Toast.makeText(
                    this,
                    "Introduz o título da tarefa",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            /*
             * Nesta fase ainda não enviamos para a API.
             *
             * Na próxima etapa vamos implementar:
             *
             * ViewModel
             *     ↓
             * Repository
             *     ↓
             * Retrofit
             *     ↓
             * POST
             */

            Toast.makeText(
                this,
                "Dados da tarefa validados",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}