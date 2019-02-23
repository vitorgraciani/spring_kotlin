package com.example.demo

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {

    fun findByName(name : String): List<User>

    fun findByNickname(nickname : String) : List<User>

    fun findByAge(age: Int) : List<User>

}