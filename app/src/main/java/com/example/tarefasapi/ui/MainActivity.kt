package com.example.tarefasapi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tarefasapi.R
import com.example.tarefasapi.viewmodel.TaskViewModel

/**
 * MainActivity
 *
 * Mostra a lista de tarefas
 */
class MainActivity : AppCompatActivity() {

    // ViewModel (MVVM)
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga layout primeiro
        setContentView(R.layout.activity_main)

        // SharedPreferences
        val prefs = getSharedPreferences("auth", MODE_PRIVATE)

        // Se não estiver logado → Login
        if (!prefs.getBoolean("logged", false)) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        // Botão Sobre
        val btnAbout = findViewById<Button>(R.id.btnAbout)

        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        // Botão Logout
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {

            prefs.edit()
                .putBoolean("logged", false)
                .apply()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerTasks)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = TaskAdapter()

        recyclerView.adapter = adapter

        // Observa tarefas
        viewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        // Carrega da API
        viewModel.loadTasks()
    }
}