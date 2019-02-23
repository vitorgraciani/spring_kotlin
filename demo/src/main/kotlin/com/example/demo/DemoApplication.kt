package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class DemoApplication {

	@Bean
	fun init(userRepository: UserRepository) = CommandLineRunner {

		userRepository.save(User(UUID.randomUUID(), "Jessy", "Maria", "Mary", Gender.FEMALE,27))
		userRepository.save(User(UUID.randomUUID(), "James", "Oliveira", "Oliver", Gender.MALE,30))
		userRepository.save(User(UUID.randomUUID(), "Giovani", "Silva", "Giant", Gender.MALE,31))
	}
}
fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

