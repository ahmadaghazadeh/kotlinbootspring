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

    fun updateCourse(courseId: Int, courseDTO: CourseDTO):CourseDTO {
       val existingCourse = courseRepository.findById(courseId)

       return if(existingCourse.isPresent){
            existingCourse.get()
                .let {
                    it.name=courseDTO.name
                    it.category=courseDTO.category
                    courseRepository.save(it)
                    CourseDTO(it.id,it.name,it.category)
                }
        }else{
            throw CourseNotFoundException(courseId.toString())

        }
    }

    fun deleteCourse(courseId: Int) {
        val existingCourse = courseRepository.findById(courseId)
        if (existingCourse.isPresent) {
            existingCourse.get()
                .let {
                    courseRepository.deleteById(courseId)
                }
        } else {
            throw CourseNotFoundException(courseId.toString())
        }

    }
}