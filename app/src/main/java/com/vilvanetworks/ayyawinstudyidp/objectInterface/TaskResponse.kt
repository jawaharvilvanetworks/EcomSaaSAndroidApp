package com.vilvanetworks.ayyawinstudyidp.objectInterface

import kotlinx.serialization.Serializable

@Serializable
data class TaskData(
    val id: Int,
    val title: String,
    val description: String,
    var disabled: Boolean,
    val task_status: Int,
    val day: Int,
    val edit: Boolean,
    var mshowhide: Boolean = false
)

@Serializable
data class TaskResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val data: List<TaskData>
)