package com.example.tarefasapi.network
import com.example.tarefasapi.model.Task
import retrofit2.Call
import retrofit2.http.GET
/**
 * Interface responsável pelas chamadas a API
 */
interface TaskService {
    //edpoint "endereço" da api
    @GET("todos")
    // devolve lista de tarefas
    fun listTasks(): Call<List<Task>>
}