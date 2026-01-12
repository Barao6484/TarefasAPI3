package com.example.tarefasapi.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tarefasapi.R

/**
 * Ecrã de login
 * Neste projeto o login é SIMULADO localmente
 * usando SharedPreferences (não há backend de autenticação)
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referências aos campos do layout
        val edtUsername = findViewById<EditText>(R.id.edtUsername)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // SharedPreferences para guardar estado de login
        val prefs: SharedPreferences =
            getSharedPreferences("auth", MODE_PRIVATE)

        // Clique no botão Entrar
        btnLogin.setOnClickListener {

            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()

            // Validação simples
            if (username.isNotEmpty() && password.isNotEmpty()) {

                // Guarda login como "true"
                prefs.edit().putBoolean("logged", true).apply()

                // Abre a MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Preenche todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}