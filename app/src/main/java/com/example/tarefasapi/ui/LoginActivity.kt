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
 * LoginActivity
 *
 * Ecrã de login da aplicação.
 * O login é SIMULADO localmente usando SharedPreferences.
 * O EMAIL é usado como identificador do utilizador.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga o layout XML à Activity
        setContentView(R.layout.activity_login)

        /**
         * SharedPreferences
         * Usado para:
         * - Guardar email e password
         * - Guardar estado de login (logged)
         */
        val prefs: SharedPreferences =
            getSharedPreferences("auth", MODE_PRIVATE)

        // Se o utilizador já estiver logado, entra direto
        if (prefs.getBoolean("logged", false)) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            return
        }

        // Referências aos campos do layout
        val edtEmail = findViewById<EditText>(R.id.edtUsername)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGoRegister = findViewById<Button>(R.id.btnGoRegister)

        /**
         * Botão "Criar Conta"
         * Abre o ecrã de registo
         */
        btnGoRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        /**
         * Botão "Entrar"
         * Valida email e password guardados
         */
        btnLogin.setOnClickListener {

            val emailInput = edtEmail.text.toString()
            val passwordInput = edtPassword.text.toString()

            // Dados guardados no registo
            val savedEmail = prefs.getString("email", null)
            val savedPassword = prefs.getString("password", null)

            // Verifica campos vazios
            if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(
                    this,
                    "Preenche todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Verifica se existe conta criada
            if (savedEmail == null || savedPassword == null) {
                Toast.makeText(
                    this,
                    "Não existe conta criada",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // Valida credenciais
            if (emailInput == savedEmail &&
                passwordInput == savedPassword
            ) {

                // Guarda estado de login
                prefs.edit()
                    .putBoolean("logged", true)
                    .apply()

                // Abre a MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()

            } else {
                Toast.makeText(
                    this,
                    "Email ou password incorretos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
