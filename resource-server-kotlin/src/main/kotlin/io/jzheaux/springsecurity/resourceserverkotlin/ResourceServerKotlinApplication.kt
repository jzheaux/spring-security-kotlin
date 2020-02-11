package io.jzheaux.springsecurity.resourceserverkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ResourceServerKotlinApplication

@RestController
class ResourceServerController {
	@GetMapping("/")
	fun index(): String {
		return "index"
	}
}

fun main(args: Array<String>) {
	runApplication<ResourceServerKotlinApplication>(*args)
}
