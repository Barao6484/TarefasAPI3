package com.example.tarefasapi.model

/**
 * Modelo que representa uma tarefa vinda da API
 */
data class Task(
    //usados para modelos de dados
    //identificador de tarefas
    val id: Int,
    //nome da tafera
    val title: String,
    //se estas concluida ou não
    val completed: Boolean
)