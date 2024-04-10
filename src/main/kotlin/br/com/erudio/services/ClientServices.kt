package br.com.erudio.services

import br.com.erudio.controller.ClientController
import br.com.erudio.data.vo.v1.ClientVO
import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.mapper.custom.ClientMapper
import br.com.erudio.model.Client
import br.com.erudio.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class ClientServices {

    @Autowired
    private lateinit var repository : ClientRepository

    @Autowired
    private lateinit var mapper : ClientMapper

    private val logger = Logger.getLogger(ClientServices::class.java.name)

    fun findAll(): List<ClientVO> {
        logger.info("finding all the clients for you...")
        val clients = repository.findAll()
        val clientVOS = DozerMapper.parseListObjects(clients, ClientVO::class.java)
        for (client in clientVOS) {
            val withSelfRel = linkTo(ClientController::class.java).slash(client.key).withSelfRel()
            client.add(withSelfRel)
        }
        return clientVOS
    }

    fun findById(id:Long): ClientVO {
        logger.info("finding a client with de id $id for you...")
        val client = repository.findById(id)
            .orElseThrow {ResourceNotFoundException("ID not founded!")}
        val clientVO: ClientVO = DozerMapper.parseObject(client, ClientVO::class.java)
        val withSelfRel = linkTo(ClientController::class.java).slash(clientVO.key).withSelfRel()
        clientVO.add(withSelfRel)
        return clientVO
    }

    fun create(client : ClientVO?): ClientVO {
        if (client == null) throw RequiredObjectIsNullException()
      logger.info("creating a client named ${client.firstName}...")
        var entity: Client = DozerMapper.parseObject(client, Client::class.java)
        val clientVO: ClientVO = DozerMapper.parseObject(repository.save(entity), ClientVO::class.java)
        val withSelfRel = linkTo(ClientController::class.java).slash(clientVO.key).withSelfRel()
        clientVO.add(withSelfRel)
        return clientVO
    }

    fun update(client : ClientVO?): ClientVO {
        if (client == null) throw RequiredObjectIsNullException()
        logger.info("updating the information of a client: id ${client.key} name ${client.firstName}...")
        val entity = repository.findById(client.key)
            .orElseThrow {ResourceNotFoundException("ID not founded!")}

        entity.firstName = client.firstName
        entity.lastName = client.lastName
        entity.address = client.address
        entity.gender = client.gender

        val clientVO: ClientVO = DozerMapper.parseObject(repository.save(entity), ClientVO::class.java)
        val withSelfRel = linkTo(ClientController::class.java).slash(clientVO.key).withSelfRel()
        clientVO.add(withSelfRel)
        return clientVO
    }

    fun delete(id: Long) {
        logger.info("deleting a client by ID $id for you...")
        val entity = repository.findById(id)
            .orElseThrow {ResourceNotFoundException("ID not founded!")}
        repository.delete(entity)
    }
}