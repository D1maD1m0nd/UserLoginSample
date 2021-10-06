package com.example.userloginsample.constants

enum class RegexValidateConstants(val value: String) {
    CORRECT_PASSWORD("^[\\S+]{5,40}"), CORRECT_LOGIN("^\\D[\\S+$]{3,40}")
}