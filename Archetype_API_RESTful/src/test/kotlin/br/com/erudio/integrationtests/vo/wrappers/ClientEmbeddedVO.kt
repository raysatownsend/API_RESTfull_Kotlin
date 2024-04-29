#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.integrationtests.vo.wrappers

import ${package}.integrationtests.vo.ClientVO
import com.fasterxml.jackson.annotation.JsonProperty

class ClientEmbeddedVO {

    @JsonProperty("clientVOList")
    var clients: List<ClientVO>?= null
}
