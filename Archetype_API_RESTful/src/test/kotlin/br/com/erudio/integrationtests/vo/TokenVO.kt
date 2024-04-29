#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.integrationtests.vo

import jakarta.xml.bind.annotation.XmlRootElement
import java.util.*

@XmlRootElement
class TokenVO (
    var username: String? = null,
    var authenticated: Boolean? = null,
    var createdAt: Date? = null,
    var expiresAt: Date? = null,
    var accessToken: String? = null,
    var refreshToken: String? = null
)
