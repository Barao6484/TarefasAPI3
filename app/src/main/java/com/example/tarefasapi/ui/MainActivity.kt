package com.example.tarefasapi.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tarefasapi.R
import com.example.tarefasapi.model.Task
import com.example.tarefasapi.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var txtTasks: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ligar o TextView do layout
        txtTasks = findViewById(R.id.txtTasks)

        // carregar tarefas da API
        loadTasks()
    }

    private fun loadTasks() {
        val call = RetrofitInitializer()
            .taskService()
            .listTasks()

        call.enqueue(object : Callback<List<Task>> {

            override fun onResponse(
                call: Call<List<Task>>,
                response: Response<List<Task>>
            ) {
                if (response.isSuccessful) {

                    val tarefas = response.body()

                    if (!tarefas.isNullOrEmpty()) {
                        val texto = StringBuilder()

                        tarefas.forEach {
                            texto.append("• ${it.title}\n\n")
                        }

                        // mostrar no ecrã
                        txtTasks.text = texto.toString()
                    } else {
                        txtTasks.text = "Nenhuma tarefa encontrada."
                    }
                } else {
                    txtTasks.text = "Erro na resposta da API."
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                Log.e("TASK_API", "Erro ao carregar tarefas", t)
                txtTasks.text = "Erro ao carregar tarefas."
            }
        })
    }
}