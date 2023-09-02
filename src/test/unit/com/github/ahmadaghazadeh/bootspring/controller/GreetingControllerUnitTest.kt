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
    : BaseTest(){


    @MockkBean
    lateinit var greetingServiceMock : GreetingService

    @Test
    fun retrieveGreeting(){

        val name= "Ahmad"

        val expectation ="$name, Hello from default profile"

        every {
            greetingServiceMock.retrieveGreeting(any())
        } returns expectation

        val result = executeGet("/v1/greetings/{name}",name)

        Assertions.assertEquals(expectation,result.responseBody)

    }


}