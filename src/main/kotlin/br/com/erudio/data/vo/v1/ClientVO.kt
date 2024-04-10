package br.com.erudio.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel

@JsonPropertyOrder("id", "lastName", "firstName", "gender", "address")
data class ClientVO(

    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,

    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
): RepresentationModel<ClientVO>()