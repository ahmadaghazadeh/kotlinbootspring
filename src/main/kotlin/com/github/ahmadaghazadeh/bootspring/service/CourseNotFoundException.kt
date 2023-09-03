package com.github.ahmadaghazadeh.bootspring.service

class CourseNotFoundException(val courseId: String) : RuntimeException("Course not found $courseId") {

}
