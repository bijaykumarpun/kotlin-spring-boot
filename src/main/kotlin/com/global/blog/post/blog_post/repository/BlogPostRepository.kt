package com.global.blog.post.blog_post.repository

import com.global.blog.post.blog_post.models.BlogPost
import org.springframework.data.jpa.repository.JpaRepository

interface BlogPostRepository : JpaRepository<BlogPost, Long>