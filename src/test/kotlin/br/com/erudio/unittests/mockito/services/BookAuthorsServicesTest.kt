package br.com.erudio.unittests.mockito.services

import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.repository.BookRepository
import br.com.erudio.services.BookAuthorsServices
import br.com.erudio.unittests.mocks.MockBook
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class BookAuthorsServicesTest {

    private lateinit var inputObject : MockBook

    @InjectMocks
    private lateinit var services : BookAuthorsServices

    @Mock
    private lateinit var repository : BookRepository

    @BeforeEach
    fun setUpMock() {
        inputObject = MockBook()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val listOfBooks = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(listOfBooks)

        val books = services.findAll()

        assertNotNull(books)
        assertEquals(14,books.size)

        val bookOne = books[1]

        assertNotNull(bookOne)
        assertNotNull(bookOne.key)
        assertNotNull(bookOne.links)
        assertTrue(bookOne.links.toString().contains("/api/books/v1/1>;rel=\"self"))
        assertEquals("Author Test1", bookOne.author)
        assertEquals(25.00, bookOne.price)
        assertEquals("Title Test1", bookOne.title)

        val bookFour = books[4]

        assertNotNull(bookFour)
        assertNotNull(bookFour.key)
        assertNotNull(bookFour.links)
        assertTrue(bookFour.links.toString().contains("/api/books/v1/4>;rel=\"self"))
        assertEquals("Author Test4", bookFour.author)
        assertEquals(25.00, bookFour.price)
        assertEquals("Title Test4", bookFour.title)

        val bookSeven = books[7]

        assertNotNull(bookSeven)
        assertNotNull(bookSeven.key)
        assertNotNull(bookSeven.links)
        assertTrue(bookSeven.links.toString().contains("/api/books/v1/7>;rel=\"self"))
        assertEquals("Author Test7", bookSeven.author)
        assertEquals(25.00, bookSeven.price)
        assertEquals("Title Test7", bookSeven.title)
    }

    @Test
    fun findById() {
        val book = inputObject.mockEntity(1)
        book.id = 1L
        `when`(repository.findById(1)).thenReturn(Optional.of(book))

        val result = services.findById(1)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/books/v1/1>;rel=\"self"))
        assertEquals("Author Test1", result.author)
        assertEquals(25.00, result.price)
        assertEquals("Title Test1", result.title)
    }

    @Test
    fun create() {
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVO(1)
        val result = services.create(vo)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/books/v1/1>;rel=\"self"))
        assertEquals("Author Test1", result.author)
        assertEquals(25.00, result.price)
        assertEquals("Title Test1", result.title)
    }

    @Test
    fun createWithNullBook() {
    val exception: Exception = assertThrows(
        RequiredObjectIsNullException::class.java
    ) {services.create(null)}
        val expectedMessage = "It's not allowed persist null objects"
        val actualMessage = exception.message
        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun update() {
        val entity = inputObject.mockEntity(1)

        val persisted = entity.copy()
        persisted.id = 1

        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        `when`(repository.save(entity)).thenReturn(persisted)

        val vo = inputObject.mockVO(1)
        val result = services.update(vo)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/books/v1/1>;rel=\"self"))
        assertEquals("Author Test1", result.author)
        assertEquals(25.00, result.price)
        assertEquals("Title Test1", result.title)
    }

    @Test
    fun updateWithNullBook() {
        val exception: Exception = assertThrows(
            RequiredObjectIsNullException::class.java
        ) {services.update(null)}
        val expectedMessage = "It's not allowed persist null objects"
        val actualMessage = exception.message
        assertTrue(actualMessage!!.contains(expectedMessage))
    }

    @Test
    fun delete() {
        val entity = inputObject.mockEntity(1)
        `when`(repository.findById(1)).thenReturn(Optional.of(entity))
        services.delete(1)
    }

}