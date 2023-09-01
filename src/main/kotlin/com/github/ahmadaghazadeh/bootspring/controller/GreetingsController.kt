package com.github.ahmadaghazadeh.bootspring.controller

import com.github.ahmadaghazadeh.bootspring.service.GreetingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/greetings")
class GreetingsController(val greetingService: GreetingService) {

    @GetMapping("/{name}")
    fun retrieveGreeting(@PathVariable("name") name: String):String{
        return greetingService.retrieveGreeting(name);
    }
}