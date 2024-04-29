#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.integrationtests.vo.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class WrapperBooksVO {

    @JsonProperty("_embedded")
    var embedded: BookEmbeddedVO? = null
}
