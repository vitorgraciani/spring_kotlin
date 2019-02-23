package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserEndpoint {

    @Autowired
    lateinit var userRepository: UserRepository

    @PostMapping
    fun createUser(@RequestBody userRequest: UserRequest): ResponseEntity<UUID>{
        val user = User(UUID.randomUUID(), userRequest.name, userRequest.lastName, userRequest.nickname, userRequest.gender, userRequest.age)
        user.validate(user)
        userRepository.save(user)
        return ResponseEntity(user.id, HttpStatus.ACCEPTED)
    }

    @GetMapping("/name/{name}")
    fun getUser(@PathVariable ("name") name : String): ResponseEntity<String> {
        val result = StringBuilder().apply{
            userRepository.findByName(name).map {  append(it)}
        }.toString()
        return ResponseEntity(result, HttpStatus.OK)
    }

    @GetMapping
    fun getAllUsers() = with(StringBuilder()) {
            userRepository.findAll().map { append(it) }
        toString()
        }


    @GetMapping("/{id}")
    fun getUser(@PathVariable ("id") id : UUID) = buildString {
        append(userRepository.findById(id))
    }

    @GetMapping("/nickname/{nickname}")
    fun getLastName(@PathVariable("nickname") nickname : String) = buildString {
        userRepository.findByNickname(nickname).map { append(it) }
    }

    @GetMapping("/age/{age}")
    fun getUserByAge(@PathVariable ("age") age : Int): ResponseEntity<List<UserResponse>>{
        val response = userRepository.findByAge(age).map { UserResponse(it.name, it.lastName, it.nickname, it.gender?.gender, it.age) }.toList()
        return ResponseEntity(response, HttpStatus.OK)
    }
}

