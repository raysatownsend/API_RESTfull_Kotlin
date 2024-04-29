#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.repository

import ${package}.model.Author
import org.springframework.data.jpa.repository.JpaRepository


interface BookRepository: JpaRepository<Author, Long?>
