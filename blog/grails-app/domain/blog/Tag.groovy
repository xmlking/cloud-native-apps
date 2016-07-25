package blog


import grails.rest.Resource

@Resource(uri="/api/tag")
class Tag {
    String name
    static constraints = {
    }
}