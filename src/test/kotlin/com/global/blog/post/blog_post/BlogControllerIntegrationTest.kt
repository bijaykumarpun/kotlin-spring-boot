import com.global.blog.post.blog_post.BlogPostApplication
import com.global.blog.post.blog_post.models.BlogPost
import com.global.blog.post.blog_post.repository.BlogPostRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

@SpringBootTest(classes = [BlogPostApplication::class])
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BlogAppIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var blogPostRepository: BlogPostRepository

    @BeforeEach
    fun setup() {
        // Clear the database before each test
        blogPostRepository.deleteAll()

        // Add some initial data for testing
        blogPostRepository.save(BlogPost(title = "First Post", content = "Content of the first post"))
        blogPostRepository.save(BlogPost(title = "Second Post", content = "Content of the second post"))
    }

    @Test
    fun `should retrieve all blog posts`() {
        mockMvc.perform(get("/api/blogs"))
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].title").value("First Post"))
            .andExpect(jsonPath("$[1].title").value("Second Post"))
    }

    @Test
    fun `should retrieve a blog post by ID`() {
        val post = blogPostRepository.findAll().first()

        mockMvc.perform(get("/api/blogs/${post.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value(post.title))
            .andExpect(jsonPath("$.content").value(post.content))
    }

    @Test
    fun `should return 404 for a non-existent blog post`() {
        mockMvc.perform(get("/api/blogs/999"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `should create a new blog post`() {
        val newPost = """
            {
                "title": "New Post",
                "content": "This is a new blog post."
            }
        """.trimIndent()

        mockMvc.perform(
            post("/api/blogs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newPost)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.title").value("New Post"))
            .andExpect(jsonPath("$.content").value("This is a new blog post."))

        assertEquals(3, blogPostRepository.count())
    }

    @Test
    fun `should update an existing blog post`() {
        val post = blogPostRepository.findAll().first()
        val updatedPost = """
            {
                "title": "Updated Title",
                "content": "Updated content."
            }
        """.trimIndent()

        mockMvc.perform(
            put("/api/blogs/${post.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedPost)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value("Updated Title"))
            .andExpect(jsonPath("$.content").value("Updated content."))

        val updatedEntity = blogPostRepository.findById(post.id!!).get()
        assertEquals("Updated Title", updatedEntity.title)
        assertEquals("Updated content.", updatedEntity.content)
    }

    @Test
    fun `should delete a blog post`() {
        val post = blogPostRepository.findAll().first()

        mockMvc.perform(delete("/api/blogs/${post.id}"))
            .andExpect(status().isNoContent)

        assertTrue(blogPostRepository.findById(post.id!!).isEmpty)
        assertEquals(1, blogPostRepository.count())
    }
}
