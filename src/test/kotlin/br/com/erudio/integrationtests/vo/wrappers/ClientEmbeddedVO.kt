package br.com.erudio.integrationtests.vo.wrappers

import br.com.erudio.integrationtests.vo.ClientVO
import com.fasterxml.jackson.annotation.JsonProperty

class ClientEmbeddedVO {

    @JsonProperty("clientVOList")
    var clients: List<ClientVO>?= null
}
