package com.github.ahmadaghazadeh.bootspring.controller

import com.github.ahmadaghazadeh.bootspring.dto.CourseDTO
import com.github.ahmadaghazadeh.bootspring.repository.CourseRepository
import com.ninjasquad.springmockk.clear
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import util.courseEntityList
import util.isEqual


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class CourseControllerIntgTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        courseRepository.saveAll(courseEntityList())
    }

    @Test
    fun addCourse(){
        val courseDTO=CourseDTO(null,"Build restful API","Ahmad Aghazadeh")
        val savedCourseDTO=webTestClient
            .post()
            .uri("/v1/courses")
            .bodyValue(courseDTO)
            .exchange()
            .expectStatus().isCreated
            .expectBody(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue{
            savedCourseDTO!!.id!=null
        }
    }



    @Test
    fun retrieveAllCourses() {

     val result=   webTestClient
            .get()
            .uri("/v1/courses")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDTO::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            result?.size== courseEntityList().size
        }

    }

}