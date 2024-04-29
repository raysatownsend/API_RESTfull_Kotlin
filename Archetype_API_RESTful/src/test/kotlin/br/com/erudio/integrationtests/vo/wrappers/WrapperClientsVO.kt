#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.integrationtests.vo.wrappers

import com.fasterxml.jackson.annotation.JsonProperty

class WrapperClientsVO {

    @JsonProperty("_embedded")
    var embedded: ClientEmbeddedVO? = null

}
