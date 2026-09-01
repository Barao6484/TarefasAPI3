package com.example.tarefasapi.network

import com.example.tarefasapi.model.CreateTaskRequest
import com.example.tarefasapi.model.Task
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Interface responsável pelas chamadas à API.
 */
interface TaskService {

    /**
     * Obtém a lista de tarefas da API.
     */
    @GET("todos")
    fun listTasks(): Call<List<Task>>

    /**
     * Cria uma nova tarefa através de um pedido POST.
     *
     * @param task dados da nova tarefa enviados no corpo do pedido.
     */
    @POST("todos")
    fun createTask(
        @Body task: CreateTaskRequest
    ): Call<Task>
}