
import com.global.blog.post.blog_post.models.BlogPost
import com.global.blog.post.blog_post.repository.BlogPostRepository
import com.global.blog.post.blog_post.service.BlogPostService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class BlogPostServiceTest {

    @Mock
    private lateinit var blogPostRepository: BlogPostRepository

    @InjectMocks
    private lateinit var blogPostService: BlogPostService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    // Test for getAllBlogPosts
    @Test
    fun `test getAllBlogPosts`() {
        val expectedBlogPosts = listOf(
            BlogPost(1L, "Random title 1", "Content body", "Author 1"),
            BlogPost(2L, "Random title 2", "Content body", "Author 2")
        )
        `when`(blogPostRepository.findAll()).thenReturn(expectedBlogPosts)

        val actualBlogPosts = blogPostService.getAllBlogPosts()

        assertEquals(expectedBlogPosts, actualBlogPosts)
        verify(blogPostRepository).findAll()
    }

    // Test for getBlogPostById
    @Test
    fun `test getBlogPostById`() {
        val id = 1L
        val expectedBlogPost = BlogPost(id, "Title 2", "Content 1", "Author 1")
        val expectedBlogPost2 = BlogPost(id, "Title 1", "Content 1", "Author 1")

        `when`(blogPostRepository.findById(id)).thenReturn(java.util.Optional.of(expectedBlogPost))

        val actualBlogPost = blogPostService.getBlogPostById(id)

        assertEquals(expectedBlogPost2, actualBlogPost)
        verify(blogPostRepository).findById(id)
    }

    // Test for createBlogPost
    @Test
    fun `test createBlogPost`() {
        val newBlogPost = BlogPost(null, "asdfasdf", "adfasdf", "New Author")
        val savedBlogPost = BlogPost(1L, "asdfasdfadsf", "asdfasdf", "New Author")
        `when`(blogPostRepository.save(newBlogPost)).thenReturn(savedBlogPost)

        val createdBlogPost = blogPostService.createBlogPost(newBlogPost)

        assertEquals(savedBlogPost, createdBlogPost)
        verify(blogPostRepository).save(newBlogPost)
    }

    // Test for updateBlogPost
    @Test
    fun `test updateBlogPost`() {
        val id = 1L
        val updatedBlogPost = BlogPost(id, "New title", "New content", "New author")
        `when`(blogPostRepository.existsById(id)).thenReturn(true)
        `when`(blogPostRepository.save(updatedBlogPost.copy(id = id))).thenReturn(updatedBlogPost)

        val result = blogPostService.updateBlogPost(id, updatedBlogPost)

        assertEquals(updatedBlogPost, result)
        verify(blogPostRepository).existsById(id)
        verify(blogPostRepository).save(updatedBlogPost.copy(id = id))
    }

    // Test for deleteBlogPost
    @Test
    fun `test deleteBlogPost`() {
        val id = 1L

        blogPostService.deleteBlogPost(id)

        verify(blogPostRepository).deleteById(id)
    }
}
