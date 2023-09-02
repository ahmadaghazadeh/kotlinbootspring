package com.github.ahmadaghazadeh.bootspring.repository

import com.github.ahmadaghazadeh.bootspring.entity.Course
import org.springframework.data.repository.CrudRepository

interface CourseRepository : CrudRepository<Course,Int> {

}