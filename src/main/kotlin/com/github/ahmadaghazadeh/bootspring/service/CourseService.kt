package com.github.ahmadaghazadeh.bootspring.service

import com.github.ahmadaghazadeh.bootspring.dto.CourseDTO
import com.github.ahmadaghazadeh.bootspring.entity.Course
import com.github.ahmadaghazadeh.bootspring.repository.CourseRepository
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class CourseService(val courseRepository: CourseRepository) {

    companion object : KLogging()

    fun addCourse(courseDTO: CourseDTO) :CourseDTO{

        val courseEntity=courseDTO.let {
            Course(null, it.name, it.category)
        }

        courseRepository.save(courseEntity)

        logger.info("Saved course is id $courseEntity")

        return courseEntity.let {
            CourseDTO(it.id,it.name,it.category)
        }

    }

    fun retrieveAllCourses(): List<CourseDTO> {
       return courseRepository.findAll()
            .map {
                CourseDTO(it.id,it.name,it.category)
            }
    }
}