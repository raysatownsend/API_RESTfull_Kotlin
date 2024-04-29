#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.exceptions

import java.util.Date

class ExceptionResponse (
        val timestamp: Date,
        val message: String?,
        val details: String
)
