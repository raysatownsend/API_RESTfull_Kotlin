#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.data.vo.v1

import java.util.*

class TokenVO (
    var username: String? = null,
    var authenticated: Boolean? = null,
    var createdAt: Date? = null,
    var expiresAt: Date? = null,
    var accessToken: String? = null,
    var refreshToken: String? = null
)
