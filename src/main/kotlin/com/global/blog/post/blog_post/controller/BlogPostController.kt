package com.global.blog.post.blog_post.controller

import com.global.blog.post.blog_post.service.BlogPostService
import com.global.blog.post.blog_post.models.BlogPost
import com.global.blog.post.blog_post.service.ApiService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("\${api.base.url}/posts")
class BlogPostController(private val blogPostService: BlogPostService, private val apiService: ApiService) {

    @GetMapping
    fun getAllBlogPosts(): ResponseEntity<List<BlogPost>> {
        return ResponseEntity.ok(blogPostService.getAllBlogPosts())
    }

    @GetMapping("/{id}")
    fun getBlogPostsById(@PathVariable id: Long): ResponseEntity<BlogPost> {
        val blogPost = blogPostService.getBlogPostById(id)
        return if (blogPost != null) {
            ResponseEntity.ok(blogPost)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createBlogPost(@RequestBody blogPost: BlogPost): ResponseEntity<BlogPost> {
        val createdBlogPost = blogPostService.createBlogPost(blogPost)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost)
    }

    @PutMapping("/{id}")
    fun updateBlogPost(@PathVariable id: Long, @RequestBody blogPost: BlogPost): ResponseEntity<BlogPost> {
        val updatedBlogPost = blogPostService.updateBlogPost(id, blogPost)
        return if (updatedBlogPost != null) {
            ResponseEntity.ok(updatedBlogPost)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteBlogPost(@PathVariable id: Long): ResponseEntity<String> {
        return if (blogPostService.getBlogPostById(id) != null) {
            blogPostService.deleteBlogPost(id)
            if (blogPostService.getBlogPostById(id) == null) {
                ResponseEntity.ok("Post successfully deleted")
            } else {
                ResponseEntity.badRequest().build()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }


}