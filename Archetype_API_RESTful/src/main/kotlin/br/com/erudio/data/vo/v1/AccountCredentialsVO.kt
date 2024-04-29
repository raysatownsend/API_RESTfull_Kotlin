#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.data.vo.v1

data class AccountCredentialsVO (
    val username: String? = null,
    val password: String? = null
)
