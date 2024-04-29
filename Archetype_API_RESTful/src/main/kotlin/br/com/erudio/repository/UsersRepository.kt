#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.repository

import ${package}.model.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


interface UsersRepository: JpaRepository<Users?, Long?> {

    @Query("SELECT u FROM Users u WHERE u.userName = :userName")
    fun findByUsername(@Param("userName") userName: String?): Users?
}
