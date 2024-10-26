package com.example.domain.utill

interface Mapper<I, O> {
    fun map(input: I): O
}