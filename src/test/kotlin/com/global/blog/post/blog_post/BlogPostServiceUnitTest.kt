import com.global.blog.post.blog_post.models.BlogPost
import com.global.blog.post.blog_post.repository.BlogPostRepository
import com.global.blog.post.blog_post.service.BlogPostService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.*

class BlogPostServiceTest {

    private lateinit var blogPostService: BlogPostService
    private val blogPostRepository: BlogPostRepository = mock()

    @BeforeEach
    fun setup() {
        blogPostService = BlogPostService(blogPostRepository)
    }

    @Test
    fun `should retrieve all blog posts`() {
        // Arrange
        val posts = listOf(
            BlogPost(id = 1, title = "First Post", content = "Content of the first post"),
            BlogPost(id = 2, title = "Second Post", content = "Content of the second post")
        )
        whenever(blogPostRepository.findAll()).thenReturn(posts)

        // Act
        val result = blogPostService.getAllBlogPosts()

        // Assert
        assertEquals(2, result.size)
        assertEquals("First Post", result[0].title)
    }

    @Test
    fun `should retrieve a blog post by ID`() {
        // Arrange
        val post = BlogPost(id = 1, title = "First Post", content = "Content of the first post")
        whenever(blogPostRepository.findById(1)).thenReturn(Optional.of(post))

        // Act
        val result = blogPostService.getPostById(1)

        // Assert
        assertTrue(result.isPresent)
        assertEquals("First Post", result.get().title)
    }

    @Test
    fun `should save a new blog post`() {
        // Arrange
        val post = BlogPost(title = "New Post", content = "This is a new blog post.")
        whenever(blogPostRepository.save(any())).thenReturn(post.copy(id = 1))

        // Act
        val result = blogPostService.savePost(post)

        // Assert
        assertNotNull(result.id)
        assertEquals("New Post", result.title)
        verify(blogPostRepository).save(post)
    }

    @Test
    fun `should delete a blog post by ID`() {
        // Arrange
        doNothing().whenever(blogPostRepository).deleteById(1)

        // Act
        blogPostService.deletePostById(1)

        // Assert
        verify(blogPostRepository).deleteById(1)
    }
}