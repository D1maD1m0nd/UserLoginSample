package com.example.userloginsample.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val login: String, val password: String) : Parcelable
