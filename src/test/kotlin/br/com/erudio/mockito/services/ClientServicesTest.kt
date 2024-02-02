package br.com.erudio.mockito.services

import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.repository.ClientRepository
import br.com.erudio.services.ClientServices
import br.com.erudio.unittests.mapper.mocks.MockClient
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
class ClientServicesTest {

    private lateinit var inputObject : MockClient

    @InjectMocks
    private lateinit var services : ClientServices

    @Mock
    private lateinit var repository : ClientRepository

    @BeforeEach
    fun setUpMock() {
        inputObject = MockClient()
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun findAll() {
        val listOfClients = inputObject.mockEntityList()
        `when`(repository.findAll()).thenReturn(listOfClients)

        val clients = services.findAll()

        assertNotNull(clients)
        assertEquals(14,clients.size)

        val clientOne = clients[1]

        assertNotNull(clientOne)
        assertNotNull(clientOne.key)
        assertNotNull(clientOne.links)
        assertTrue(clientOne.links.toString().contains("/api/client/v1/1>;rel=\"self"))
        assertEquals("Address Test1", clientOne.address)
        assertEquals("First Name Test1", clientOne.firstName)
        assertEquals("Last Name Test1", clientOne.lastName)
        assertEquals("Female", clientOne.gender)

        val clientFour = clients[4]

        assertNotNull(clientFour)
        assertNotNull(clientFour.key)
        assertNotNull(clientFour.links)
        assertTrue(clientFour.links.toString().contains("/api/client/v1/4>;rel=\"self"))
        assertEquals("Address Test4", clientFour.address)
        assertEquals("First Name Test4", clientFour.firstName)
        assertEquals("Last Name Test4", clientFour.lastName)
        assertEquals("Male", clientFour.gender)

        val clientSeven = clients[7]

        assertNotNull(clientSeven)
        assertNotNull(clientSeven.key)
        assertNotNull(clientSeven.links)
        assertTrue(clientSeven.links.toString().contains("/api/client/v1/7>;rel=\"self"))
        assertEquals("Address Test7", clientSeven.address)
        assertEquals("First Name Test7", clientSeven.firstName)
        assertEquals("Last Name Test7", clientSeven.lastName)
        assertEquals("Female", clientSeven.gender)
    }

    @Test
    fun findById() {
        val client = inputObject.mockEntity(1)
        client.id = 1L
        `when`(repository.findById(1)).thenReturn(Optional.of(client))

        val result = services.findById(1)

        assertNotNull(result)
        assertNotNull(result.key)
        assertNotNull(result.links)
        assertTrue(result.links.toString().contains("/api/client/v1/1>;rel=\"self"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
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
        assertTrue(result.links.toString().contains("/api/client/v1/1>;rel=\"self"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun createWithNullClient() {
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
        assertTrue(result.links.toString().contains("/api/client/v1/1>;rel=\"self"))
        assertEquals("Address Test1", result.address)
        assertEquals("First Name Test1", result.firstName)
        assertEquals("Last Name Test1", result.lastName)
        assertEquals("Female", result.gender)
    }

    @Test
    fun updateWithNullClient() {
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