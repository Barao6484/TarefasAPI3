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

        /**
         * SharedPreferences
         * Usado para verificar se o utilizador está logado
         */
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)

        // 🔐 CONTROLO DE ACESSO
        // Se NÃO estiver logado, volta imediatamente para o Login
        if (!prefs.getBoolean("logged", false)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Liga o layout XML à Activity
        setContentView(R.layout.activity_main)

        /**
         * Botão Logout
         * Apaga o estado de login e volta ao LoginActivity
         */
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {

            // Remove o estado de login
            prefs.edit()
                .putBoolean("logged", false)
                .apply()

            // Volta para o Login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        /**
         * RecyclerView
         * Mostra a lista de tarefas
         */
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adapter da lista
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter

        /**
         * Observa os dados do ViewModel
         * Sempre que a lista muda, o RecyclerView atualiza
         */
        viewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        // Carrega tarefas da API
        viewModel.loadTasks()
    }
}