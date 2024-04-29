#set( $symbol_pound = '#' )\n#set( $symbol_dollar = '$' )\n#set( $symbol_escape = '\' )\npackage ${package}.data.vo.v1

class UploadFileResponseVO (
    var filename: String? = "",
    var fileDownloadUri: String = "",
    var fileType: String = "",
    var fileSize: Long = 0
)
