package com.github.ahmadaghazadeh.bootspring.controller

import com.github.ahmadaghazadeh.bootspring.service.GreetingService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest(controllers = [GreetingsController::class])
class GreetingControllerUnitTest
{

    @Autowired
    lateinit var webTestClient : WebTestClient

    @MockkBean
    lateinit var greetingServiceMock : GreetingService

    @Test
    fun retrieveGreeting(){

        val name= "Ahmad"

        val expectation ="$name, Hello from default profile"

        every {
            greetingServiceMock.retrieveGreeting(any())
        } returns expectation

        val result=webTestClient.get()
            .uri("/v1/greetings/{name}",name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals(expectation,result.responseBody)

    }
}