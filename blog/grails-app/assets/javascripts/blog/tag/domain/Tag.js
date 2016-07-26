//= wrapped
//= require /angular/angular-resource

angular
    .module("blog.tag")
    .factory("Tag", Tag);

function Tag($resource) {
    var Tag = $resource(
        "api/tag/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );

    Tag.list = Tag.query;

    Tag.prototype.toString = function() {
        return 'blog.Tag : ' + (this.id ? this.id : '(unsaved)');
    };

    return Tag;
}
