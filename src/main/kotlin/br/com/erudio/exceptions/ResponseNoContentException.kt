package br.com.erudio.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NO_CONTENT)
class ResponseNoContentException: RuntimeException {
    constructor(): super("No content found. Please verify the data you're requesting")
    constructor(exception: String): super(exception)
}