package br.com.erudio.services

import br.com.erudio.controller.AuthorController
import br.com.erudio.data.vo.v1.AuthorVO
import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.mapper.custom.AuthorMapper
import br.com.erudio.model.Author
import br.com.erudio.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookAuthorsServices {

    @Autowired
    private lateinit var repository : BookRepository

    @Autowired
    private lateinit var mapper : AuthorMapper

    private val logger = Logger.getLogger(BookAuthorsServices::class.java.name)

    fun findAll(): List<AuthorVO> {
        logger.info("finding all books for you...")
        val authors = repository.findAll()
        val authorVOS = DozerMapper.parseListObjects(authors, AuthorVO::class.java)
        for (author in authorVOS) {
            val withSelfRel = linkTo(AuthorController::class.java).slash(author.key).withSelfRel()
            author.add(withSelfRel)
        }
        return authorVOS
    }

    fun findById(id:Long): AuthorVO {
        logger.info("finding a book with de id $id for you...")
        val author = repository.findById(id)
            .orElseThrow {ResourceNotFoundException("ID not founded!")}
        val authorVO: AuthorVO = DozerMapper.parseObject(author, AuthorVO::class.java)
        val withSelfRel = linkTo(AuthorController::class.java).slash(authorVO.key).withSelfRel()
        authorVO.add(withSelfRel)
        return authorVO
    }

    fun create(author : AuthorVO?): AuthorVO {
        if (author == null) throw RequiredObjectIsNullException()
      logger.info("creating a book named ${author.title}...")
      var entity: Author = DozerMapper.parseObject(author, Author::class.java)
        val authorVO: AuthorVO = DozerMapper.parseObject(repository.save(entity), AuthorVO::class.java)
        val withSelfRel = linkTo(AuthorController::class.java).slash(authorVO.key).withSelfRel()
        authorVO.add(withSelfRel)
        return authorVO
    }

    fun update(author : AuthorVO?): AuthorVO {
        if (author == null) throw RequiredObjectIsNullException()
        logger.info("updating the information of the book named: id ${author.title} wrote by ${author.author}...")
        val entity = repository.findById(author.key)
            .orElseThrow {ResourceNotFoundException("ID not founded!")}

        entity.author = author.author
        entity.launchDate = author.launchDate
        entity.price = author.price
        entity.title = author.title

        val authorVO: AuthorVO = DozerMapper.parseObject(repository.save(entity), AuthorVO::class.java)
        val withSelfRel = linkTo(AuthorController::class.java).slash(authorVO.key).withSelfRel()
        authorVO.add(withSelfRel)
        return authorVO
    }

    fun delete(id: Long) {
        logger.info("deleting the book by ID $id for you...")
        val entity = repository.findById(id)
            .orElseThrow {ResourceNotFoundException("ID not founded!")}
        repository.delete(entity)
    }
}