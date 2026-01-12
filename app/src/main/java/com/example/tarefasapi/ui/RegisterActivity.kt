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
 * RegisterActivity
 *
 * Permite criar uma conta localmente
 * usando SharedPreferences (sem backend)
 */
class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Campos do formulário
        val edtUsername = findViewById<EditText>(R.id.edtRegisterUsername)
        val edtPassword = findViewById<EditText>(R.id.edtRegisterPassword)
        val edtConfirm = findViewById<EditText>(R.id.edtRegisterConfirm)
        val btnCreate = findViewById<Button>(R.id.btnCreateAccount)

        // SharedPreferences
        val prefs: SharedPreferences =
            getSharedPreferences("auth", MODE_PRIVATE)

        // Botão Criar Conta
        btnCreate.setOnClickListener {

            val username = edtUsername.text.toString()
            val password = edtPassword.text.toString()
            val confirm = edtConfirm.text.toString()

            // Validações
            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Preenche todos os campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(this, "As passwords não coincidem", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guarda os dados do utilizador
            prefs.edit()
                .putString("username", username)
                .putString("password", password)
                .apply()

            Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()

            // Volta para o Login
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
