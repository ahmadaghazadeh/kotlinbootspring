package com.github.ahmadaghazadeh.bootspring.controller

import com.github.ahmadaghazadeh.bootspring.dto.CourseDTO
import com.github.ahmadaghazadeh.bootspring.service.CourseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/courses")
@Validated
class  CourseController(val courseService: CourseService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addCourse(@RequestBody @Valid courseDTO: CourseDTO): CourseDTO{
        return courseService.addCourse(courseDTO)
    }

    @GetMapping
    fun retrieveAllCourses(): List<CourseDTO> =courseService.retrieveAllCourses()

    @PutMapping("/{course_id}")
    fun updateCourse(@PathVariable("course_id") courseId: Int,@RequestBody courseDTO: CourseDTO) : CourseDTO =
        courseService.updateCourse(courseId,courseDTO)

    @DeleteMapping("/{course_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable("course_id") courseId : Int){
        courseService.deleteCourse(courseId)
    }
}