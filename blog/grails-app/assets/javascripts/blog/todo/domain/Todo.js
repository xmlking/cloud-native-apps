//= wrapped
//= require /angular/angular-resource

angular
    .module("blog.todo")
    .factory("Todo", Todo);

function Todo($resource) {
    var Todo = $resource(
        "api/todos/:id",
        {"id": "@id"},
        {"update": {method: "PUT"},
         "query": {method: "GET", isArray: true},
         "get": {method: 'GET'}}
    );

    Todo.list = Todo.query;

    Todo.prototype.toString = function() {
        return 'blog.Todo : ' + (this.id ? this.id : '(unsaved)');
    };

    return Todo;
}
