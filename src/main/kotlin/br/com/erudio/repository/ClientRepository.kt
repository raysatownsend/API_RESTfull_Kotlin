package br.com.erudio.repository

import br.com.erudio.model.Client
import org.springframework.data.jpa.repository.JpaRepository


interface ClientRepository: JpaRepository<Client, Long?>