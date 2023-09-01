package com.github.ahmadaghazadeh.bootspring.service

import org.springframework.stereotype.Service

@Service
class GreetingService {
    fun retrieveGreeting(name: String)="Hello 1 $name"
}