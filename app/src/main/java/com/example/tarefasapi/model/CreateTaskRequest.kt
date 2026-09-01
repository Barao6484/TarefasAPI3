package com.example.tarefasapi.model

/**
 * Modelo utilizado para enviar uma nova tarefa para a API.
 *
 * Este objeto representa os dados enviados
 * através de um pedido POST.
 */
data class CreateTaskRequest(

    // Título da nova tarefa.
    val title: String,

    // Indica se a tarefa está concluída.
    val completed: Boolean = false,

    // Identificador do utilizador.
    val userId: Int = 1
)