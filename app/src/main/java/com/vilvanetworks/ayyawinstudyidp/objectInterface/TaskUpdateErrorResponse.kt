package com.vilvanetworks.ayyawinstudyidp.objectInterface

data class TaskUpdateErrorResponse(
    val code: Int,
    val success: Boolean,
    val message: String,
    val errors: TaskUpdateErrors
)

data class TaskUpdateErrors(
    val status: List<String>,
    val description: List<String>,
    val media_gallery: List<String>
)