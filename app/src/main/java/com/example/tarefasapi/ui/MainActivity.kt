package com.example.tarefasapi.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarefasapi.R
import com.example.tarefasapi.viewmodel.TaskViewModel
import android.widget.Button
import android.content.Intent

/**
 * MainActivity
 *
 * Responsável por:
 * - Inicializar a interface
 * - Configurar o RecyclerView
 * - Observar os dados do ViewModel
 * - Atualizar a lista de tarefas no ecrã
 */
class MainActivity : AppCompatActivity() {

    // ViewModel ligado ao ciclo de vida da Activity (MVVM)
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga o layout XML à Activity
        setContentView(R.layout.activity_main)

        //Refência ao botão Logout
        val btnLogout = findViewById<Button>(R.id.btnLogout)

// SharedPreferences
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)

// Clique no botão Logout
        btnLogout.setOnClickListener {

            // Apaga o estado de login
            prefs.edit().putBoolean("logged", false).apply()

            // Volta para o LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Referência ao RecyclerView definido no XML
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTasks)

        // Define o layout da lista (vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cria o adapter da lista
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter

        /**
         * Observa a lista de tarefas vinda do ViewModel.
         * Sempre que os dados mudarem:
         * - O adapter é atualizado
         * - A interface é automaticamente redesenhada
         */
        viewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        // Inicia o carregamento das tarefas da API
        viewModel.loadTasks()
    }
}