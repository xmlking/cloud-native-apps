//= wrapped
//= require /angular/angular-resource

angular
    .module("blog.comment")
    .factory("Comment", Comment);

function Comment($resource, domainListConversion, domainConversion) {
    var Comment = $resource(
        "api/comment/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true, transformResponse: [angular.fromJson, domainListConversion("Post", "post", "domainConversion")]},
         "get": {method: 'GET', transformResponse: [angular.fromJson, domainConversion("Post", "post")]}}
    );

    Comment.list = Comment.query;

    Comment.prototype.toString = function() {
        return 'blog.Comment : ' + (this.id ? this.id : '(unsaved)');
    };

    return Comment;
}
