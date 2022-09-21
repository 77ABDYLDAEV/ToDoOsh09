package com.example.todoosh09

import java.io.Serializable

data class TaskModel(
    val task:String,
    val date:String,
    val regular:String

):Serializable