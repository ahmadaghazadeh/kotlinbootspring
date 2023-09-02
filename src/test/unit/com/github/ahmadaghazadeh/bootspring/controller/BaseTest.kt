package com.github.ahmadaghazadeh.bootspring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient

@WebMvcTest()
open class BaseTest{


    @Autowired
    lateinit var webTestClient : WebTestClient

    fun executeGet(url: String,name: String): EntityExchangeResult<String> {
        return webTestClient.get()
            .uri(url, name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()
    }
}