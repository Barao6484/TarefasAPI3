package com.example.tarefasapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tarefasapi.model.Task
import com.example.tarefasapi.repository.TaskRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TaskViewModel
 *
 * Responsável por:
 * - Comunicar com o Repository
 * - Carregar as tarefas da API
 * - Disponibilizar as tarefas através de LiveData
 * - Informar a interface quando ocorre um erro
 *
 * Esta classe segue o padrão MVVM.
 */
class TaskViewModel : ViewModel() {

    // Repository responsável pelas chamadas à API.
    private val repository = TaskRepository()

    // Lista privada de tarefas que pode ser alterada pelo ViewModel.
    private val _tasks = MutableLiveData<List<Task>>()

    // Lista pública de tarefas que pode ser observada pela Activity.
    val tasks: LiveData<List<Task>> = _tasks

    // Mensagem de erro privada que pode ser alterada pelo ViewModel.
    private val _error = MutableLiveData<String?>()

    // Mensagem de erro pública que pode ser observada pela Activity.
    val error: LiveData<String?> = _error

    /**
     * loadTasks()
     *
     * Solicita à API a lista de tarefas.
     */
    fun loadTasks() {

        // Faz a chamada à API através do Repository.
        repository.getTasks().enqueue(object : Callback<List<Task>> {

            /**
             * Executado quando a API responde.
             */
            override fun onResponse(
                call: Call<List<Task>>,
                response: Response<List<Task>>
            ) {

                // Verifica se a resposta HTTP foi bem-sucedida.
                if (response.isSuccessful) {

                    // Obtém a lista devolvida pela API.
                    val taskList = response.body()

                    // Verifica se a API realmente devolveu dados.
                    if (taskList != null) {

                        // Disponibiliza as tarefas para a interface.
                        _tasks.value = taskList

                        // Limpa qualquer erro anterior.
                        _error.value = null

                    } else {

                        // Informa que não foram recebidas tarefas.
                        _error.value = "Não foram encontradas tarefas."
                    }

                } else {

                    // Informa que a API respondeu com um erro HTTP.
                    _error.value =
                        "Erro ao carregar tarefas. Código: ${response.code()}"
                }
            }

            /**
             * Executado quando não é possível contactar a API.
             */
            override fun onFailure(
                call: Call<List<Task>>,
                t: Throwable
            ) {

                // Informa o utilizador de que houve um problema de ligação.
                _error.value =
                    "Não foi possível contactar a API. Verifique a ligação à Internet."
            }
        })
    }
}