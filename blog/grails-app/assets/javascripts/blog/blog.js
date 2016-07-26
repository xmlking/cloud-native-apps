//= wrapped
//= require /angular/angular
//= require /blog/core/blog.core
//= require /blog/index/blog.index
//= require /blog/tag/blog.tag
//= require /blog/post/blog.post
//= require /blog/comment/blog.comment
//= require /blog/todo/blog.todo

angular.module("blog", [
        "blog.core",
        "blog.index",
        "blog.tag",
        "blog.post",
        "blog.comment",
        "blog.todo"
    ]);
