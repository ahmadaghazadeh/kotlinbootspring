package com.github.ahmadaghazadeh.bootspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BootspringApplication

fun main(args: Array<String>) {
	runApplication<BootspringApplication>(*args)
}
