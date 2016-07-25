package blog


import grails.rest.*

@Resource(uri="/api/post")
class Post {
    URL url
    PostContent postContent
    static embedded = ['postContent']
    static hasMany = [comments: Comment , tags: Tag]
    static constraints = {
    }
}