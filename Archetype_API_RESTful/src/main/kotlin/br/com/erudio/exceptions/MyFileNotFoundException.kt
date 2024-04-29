#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class MyFileNotFoundException : RuntimeException{

    constructor(exception : String) : super(exception)
    constructor(exception : String, cause: Throwable) : super(exception, cause)
}
