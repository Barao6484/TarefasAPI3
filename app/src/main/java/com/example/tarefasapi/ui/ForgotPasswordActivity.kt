package com.example.tarefasapi.ui
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tarefasapi.R

class ForgotPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Liga o layout XML
        setContentView(R.layout.activity_forgot_password)

        // Referências aos campos
        val edtEmail = findViewById<EditText>(R.id.edtRecoveryEmail)
        val edtNewPassword =
            findViewById<EditText>(R.id.edtNewPassword)

        val edtConfirmPassword =
            findViewById<EditText>(R.id.edtConfirmNewPassword)

        val btnResetPassword =
            findViewById<Button>(R.id.btnResetPassword)

        // SharedPreferences
        val prefs: SharedPreferences =
            getSharedPreferences("auth", MODE_PRIVATE)

        /**
         * Botão para alterar a password.
         */
        btnResetPassword.setOnClickListener {

            // Obtém os valores introduzidos
            val emailInput =
                edtEmail.text.toString().trim()

            val newPassword =
                edtNewPassword.text.toString()

            val confirmPassword =
                edtConfirmPassword.text.toString()

            // Email guardado no registo
            val savedEmail =
                prefs.getString("email", null)

            // Verifica campos vazios
            if (
                emailInput.isEmpty() ||
                newPassword.isEmpty() ||
                confirmPassword.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preenche todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Verifica se existe uma conta
            if (savedEmail == null) {

                Toast.makeText(
                    this,
                    "Não existe nenhuma conta registada",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Verifica se o email corresponde
            if (emailInput != savedEmail) {

                Toast.makeText(
                    this,
                    "Email não encontrado",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Verifica se as passwords coincidem
            if (newPassword != confirmPassword) {

                Toast.makeText(
                    this,
                    "As passwords não coincidem",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Validação mínima da password
            if (newPassword.length < 4) {

                Toast.makeText(
                    this,
                    "A password deve ter pelo menos 4 caracteres",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            // Guarda a nova password
            prefs.edit()
                .putString("password", newPassword)
                .apply()

            Toast.makeText(
                this,
                "Password alterada com sucesso",
                Toast.LENGTH_SHORT
            ).show()

            // Volta ao Login
            startActivity(
                Intent(this, LoginActivity::class.java)
            )

            finish()
        }
    }
}