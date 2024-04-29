#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.data.vo.v1

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.github.dozermapper.core.Mapping
import org.springframework.hateoas.RepresentationModel
import java.util.*

@JsonPropertyOrder("id", "author", "launchDate", "price", "title")
data class AuthorVO(
    @Mapping("id")
    @field:JsonProperty("id")
    var key: Long = 0,

    var author: String = "",
    var launchDate: Date? = null,
    var price: Double = 25.00,
    var title: String = "",
): RepresentationModel<AuthorVO>()
