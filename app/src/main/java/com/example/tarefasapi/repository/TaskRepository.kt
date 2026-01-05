package com.example.tarefasapi.repository
import com.example.tarefasapi.model.Task
import com.example.tarefasapi.network.RetrofitInitializer
import retrofit2.Call

class TaskRepository {
    fun getTasks(): Call<List<Task>> {
        return RetrofitInitializer()
            .taskService()
            .listTasks()
    }
}