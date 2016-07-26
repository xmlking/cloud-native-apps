//= wrapped
//= require /angular/angular-resource

angular
    .module("blog.post")
    .factory("Post", Post);

function Post($resource, domainListConversion, domainToManyConversion) {
    var Post = $resource(
        "api/post/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true, transformResponse: [angular.fromJson, domainListConversion("Comment", "comments", "domainToManyConversion"), domainListConversion("Tag", "tags", "domainToManyConversion")]},
         "get": {method: 'GET', transformResponse: [angular.fromJson, domainToManyConversion("Comment", "comments"), domainToManyConversion("Tag", "tags")]}}
    );

    Post.list = Post.query;

    Post.prototype.toString = function() {
        return 'blog.Post : ' + (this.id ? this.id : '(unsaved)');
    };

    return Post;
}
