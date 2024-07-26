package com.global.blog.post.blog_post

import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@Transactional

class BlogPostService(private val blogPostRepository: BlogPostRepository) {
    fun getAllBlogPosts(): List<BlogPost> = blogPostRepository.findAll()
    fun getBlogPostById(id: Long): BlogPost? = blogPostRepository.findById(id).orElse(null)
    fun createBlogPost(blogPost: BlogPost): BlogPost = blogPostRepository.save(blogPost)
    fun updateBlogPost(id: Long, blogPost: BlogPost): BlogPost? {
        return if (blogPostRepository.existsById(id)) {
            blogPostRepository.save(blogPost.copy(id = id))
        } else null
    }

    fun deleteBlogPost(id: Long) {
        blogPostRepository.deleteById(id)
    }
}