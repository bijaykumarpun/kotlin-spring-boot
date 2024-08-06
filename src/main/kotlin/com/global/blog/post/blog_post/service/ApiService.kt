package com.global.blog.post.blog_post.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ApiService {
    // Inject the base URL from the porperties file
    @Value("\${api.base.url")
    private lateinit var baseUrl: String

    // Define the enedpoint URL using the base URL
    fun getBaseUrl(): String {
        return baseUrl
    }

}