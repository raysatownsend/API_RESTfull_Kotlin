package br.com.erudio.integrationtests.vo.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class WrapperBooksVO {

    @JsonProperty("_embedded")
    var embedded: BookEmbeddedVO? = null
}
