package com.github.ahmadaghazadeh.bootspring.controller

import com.github.ahmadaghazadeh.bootspring.dto.CourseDTO
import com.github.ahmadaghazadeh.bootspring.service.CourseService
import com.github.ahmadaghazadeh.bootspring.service.GreetingService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
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

}