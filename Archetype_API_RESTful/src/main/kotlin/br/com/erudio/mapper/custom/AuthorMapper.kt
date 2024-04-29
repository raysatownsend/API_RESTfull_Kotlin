#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.mapper.custom

import ${package}.data.vo.v1.AuthorVO
import ${package}.model.Author
import org.springframework.stereotype.Service

@Service
class AuthorMapper {

    fun mapEntityToVO(author : Author): AuthorVO {
        val vo = AuthorVO()

        vo.key = author.id
        vo.author = author.author
        vo.price = author.price
        vo.price = author.price
        vo.title = author.title
        vo.launchDate = author.launchDate

        return vo
    }
    fun mapVOToEntity(author : AuthorVO): Author {
        val entity = Author()

        entity.id = author.key
        entity.author = author.author
        entity.price = author.price
        entity.price = author.price
        entity.title = author.title
        entity.launchDate = author.launchDate
        return entity
    }
}
