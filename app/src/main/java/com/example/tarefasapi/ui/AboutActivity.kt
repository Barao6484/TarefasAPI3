package com.example.tarefasapi.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tarefasapi.R

/**
 * AboutActivity
 *
 * Mostra informações do projeto
 * e permite ir para o Login
 */
class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga o layout XML
        setContentView(R.layout.activity_about)

        // Referência ao botão "Entrar"
        val btnEntrar = findViewById<Button>(R.id.btnEntrar)

        // Clique no botão → abre Login
        btnEntrar.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}