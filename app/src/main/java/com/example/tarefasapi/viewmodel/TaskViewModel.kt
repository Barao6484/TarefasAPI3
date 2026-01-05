package com.example.tarefasapi.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tarefasapi.model.Task
import com.example.tarefasapi.repository.TaskRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun loadTasks() {
        repository.getTasks().enqueue(object : Callback<List<Task>> {

            override fun onResponse(
                call: Call<List<Task>>,
                response: Response<List<Task>>
            ) {
                if (response.isSuccessful) {
                    _tasks.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Task>>, t: Throwable) {
                // aqui podíamos tratar erros
            }
        })
    }

}