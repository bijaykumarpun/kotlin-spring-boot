package com.global.blog.post.blog_post

import org.springframework.data.jpa.repository.JpaRepository

interface BlogPostRepository : JpaRepository<BlogPost, Long>