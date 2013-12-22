package com.share3d.sys

class Attachment {
    transient grailsApplication
    String name
    String path
    String fileType
    Long size

    Date dateCreated
    static constraints = {
    }
    String getRealPath(){
        def attaRootPath = grailsApplication.config.attachmentDir
        log.debug(attaRootPath)
        return "${attaRootPath}${this.path}"
    }
}
