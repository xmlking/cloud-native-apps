package blog


import grails.rest.Resource

@Resource(uri="/api/comment")
class Comment {
    String text
    static belongsTo = [post: Post]
    static constraints = {
    }
}