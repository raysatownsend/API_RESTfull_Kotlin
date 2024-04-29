package br.com.erudio.repository

import br.com.erudio.model.Author
import org.springframework.data.jpa.repository.JpaRepository


interface BookRepository: JpaRepository<Author, Long?>
