package com.github.ahmadaghazadeh.bootspring.dto

import jakarta.validation.constraints.NotBlank

data class CourseDTO(
    val id:Int?,
    @get:NotBlank(message="CourseDTO.name must not be empty")
    val name:String,
    @get:NotBlank(message="CourseDTO.category must not be empty")
    val category:String,
)
