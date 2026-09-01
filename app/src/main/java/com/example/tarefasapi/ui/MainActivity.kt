package com.example.tarefasapi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarefasapi.R
import com.example.tarefasapi.viewmodel.TaskViewModel

/**
 * MainActivity
 *
 * Mostra a lista de tarefas da aplicação.
 *
 * Utiliza:
 * - MVVM através do TaskViewModel
 * - RecyclerView para apresentar tarefas
 * - SharedPreferences para controlo de autenticação
 */
class MainActivity : AppCompatActivity() {

    // ViewModel responsável pela lógica relacionada com as tarefas.
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga o layout activity_main.xml à Activity.
        setContentView(R.layout.activity_main)

        // =====================================================
        // SHARED PREFERENCES
        // =====================================================

        // Obtém as preferências utilizadas para autenticação.
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)

        // Se o utilizador não estiver autenticado,
        // volta para o ecrã de Login.
        if (!prefs.getBoolean("logged", false)) {

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
            return
        }

        // =====================================================
        // BOTÃO SOBRE
        // =====================================================

        val btnAbout = findViewById<Button>(R.id.btnAbout)

        btnAbout.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    AboutActivity::class.java
                )
            )
        }

        // =====================================================
        // BOTÃO LOGOUT
        // =====================================================

        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {

            // Remove o estado de autenticação.
            prefs.edit()
                .putBoolean("logged", false)
                .apply()

            // Volta para o Login.
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            // Fecha a MainActivity.
            finish()
        }

        // =====================================================
        // BOTÃO ADICIONAR TAREFA
        // =====================================================

        val btnAddTask = findViewById<Button>(R.id.btnAddTask)

        /**
         * Abre o ecrã responsável pela criação
         * de uma nova tarefa.
         */
        btnAddTask.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    CreateTaskActivity::class.java
                )
            )
        }

        // =====================================================
        // RECYCLERVIEW
        // =====================================================

        // Obtém a RecyclerView do layout.
        val recyclerView =
            findViewById<RecyclerView>(R.id.recyclerTasks)

        // Define que a lista será apresentada verticalmente.
        recyclerView.layoutManager =
            LinearLayoutManager(this)

        // Cria o Adapter responsável por apresentar as tarefas.
        val adapter = TaskAdapter()

        // Liga o Adapter à RecyclerView.
        recyclerView.adapter = adapter

        // =====================================================
        // OBSERVADOR DAS TAREFAS
        // =====================================================

        // Observa as alterações na lista de tarefas.
        viewModel.tasks.observe(this) { tasks ->

            // Atualiza a RecyclerView quando os dados chegam.
            adapter.updateTasks(tasks)
        }

        // =====================================================
        // OBSERVADOR DE ERROS
        // =====================================================

        // Observa mensagens de erro provenientes do ViewModel.
        viewModel.error.observe(this) { errorMessage ->

            // Verifica se existe uma mensagem de erro.
            if (errorMessage != null) {

                // Mostra o erro ao utilizador.
                Toast.makeText(
                    this,
                    errorMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        // =====================================================
        // CARREGAR TAREFAS DA API
        // =====================================================

        // Pede ao ViewModel para carregar as tarefas.
        viewModel.loadTasks()
    }
}