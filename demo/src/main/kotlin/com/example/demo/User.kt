package com.example.demo

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "USER")
data class User (
        @Id
        val id: UUID = UUID.randomUUID(),
        @Column(name = "name")
        val name : String?,
        @Column(name="lastName")
        val lastName: String?,
        @Column(name= "nickname")
        val nickname : String?,
        @Column(name ="gender")
        val gender : Gender?,
        @Column(name="age")
        val age : Int?
){

        fun validateBeforeSave() {
                fun <T> validate(attr: T, name: String) {
                        println("starting validation $name")
                        if (attr == null) {
                                throw IllegalArgumentException("O user de id = $id tem o campo $name nulo, por isso nao pode ser salvo")
                        }
                }
                validate(name, "name")
                validate(nickname, "nickname")
                validate(age, "age")
                validate(gender, "gender")
                validate(lastName, "lastName")

        }
        override fun toString(): String {
                return "User [id = $id, Name = $name, last name = $lastName, nickname = $nickname  gender = $gender and age = $age] </br>"
        }

        fun validate(user : User){
                user.validateBeforeSave()
        }
}