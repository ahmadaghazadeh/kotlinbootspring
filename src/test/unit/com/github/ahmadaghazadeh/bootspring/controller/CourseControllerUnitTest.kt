package com.github.ahmadaghazadeh.bootspring.controller

import com.github.ahmadaghazadeh.bootspring.dto.CourseDTO
import com.github.ahmadaghazadeh.bootspring.entity.Course
import com.github.ahmadaghazadeh.bootspring.service.CourseService
import com.github.ahmadaghazadeh.bootspring.service.GreetingService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import util.courseDTO

@WebMvcTest(controllers = [CourseController::class])
class CourseControllerUnitTest  : BaseTest(){

    @MockkBean
    lateinit var courseServiceMock : CourseService

    @Test
    fun addCourse() {
        //given
        val courseDTO = courseDTO()

        every { courseServiceMock.addCourse(any()) } returns courseDTO(id=1)

        //when
        val savedCourseDTO = webTestClient
            .post()
            .uri("/v1/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        //then
        Assertions.assertTrue {
            savedCourseDTO!!.id != null
        }
    }

    @Test
    fun retrieveAllCourses() {

        every { courseServiceMock.retrieveAllCourses() }.returnsMany(
            listOf(
                CourseDTO(1,
                    "Build RestFul APis using Spring Boot and Kotlin", "Development" ),
                CourseDTO(2,
                    "Build Reactive Microservices using Spring WebFlux/SpringBoot", "Development" ,
                    )
            )
        )


        val courseDTOs = webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        println("courseDTOs : $courseDTOs")

        Assertions.assertEquals(2, courseDTOs!!.size)

    }

    @Test
    fun updateCourse() {

        val updatedCourseEntity = Course(null,
            "Apache Kafka for Developers using Spring Boot1", "Development" )

        every { courseServiceMock.updateCourse(any(), any()) } returns CourseDTO(100,
            "Apache Kafka for Developers using Spring Boot1", "Development" )


        val updatedCourseDTO = webTestClient
            .put()
            .uri("/v1/courses/{courseId}", 100)
            .bodyValue(updatedCourseEntity)
            .exchange()
            .expectStatus().isOk
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals("Apache Kafka for Developers using Spring Boot1", updatedCourseDTO?.name)

    }

    @Test
    fun deleteCourse() {

        every { courseServiceMock.deleteCourse(any()) } just runs

        webTestClient
            .delete()
            .uri("/v1/courses/{courseId}", 100)
            .exchange()
            .expectStatus().isNoContent

        verify(exactly = 1) { courseServiceMock.deleteCourse(any()) }

    }

    @Test
    fun addCourse_validation() {
        //given
        val courseDTO = courseDTO(name = "", category = "")

        every { courseServiceMock.addCourse(any()) } returns courseDTO(id=1)

        //when
        val response = webTestClient
            .post()
            .uri("/v1/courses")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isBadRequest


        println("response : $response")
        assertEquals("courseDTO.category must not be blank, courseDTO.name must not be blank"
            , response)
    }


}