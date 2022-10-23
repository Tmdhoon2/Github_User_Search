package com.example.gitgit.model

data class UserResponse(
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val company: String,
    val created_at: String,
    val email: String,
    val followers: Int,
    val following: Int,
    val html_url: String,
    val location: String,
    val login: String,
    val name: String,
    val public_repos: Int
)
