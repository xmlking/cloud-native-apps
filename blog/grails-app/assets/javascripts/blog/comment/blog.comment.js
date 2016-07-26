//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /blog/core/blog.core
//= require /blog/post/blog.post
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("blog.comment", [
    "ui.router",
    "ngResource",
    "blog.core",
    "blog.post"
]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('comment', {
            url: "/comment",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('comment.list', {
            url: "",
            templateUrl: "/blog/comment/list.html",
            controller: "CommentListController as vm"
        })
        .state('comment.create', {
            url: "/create",
            params: {"postId":null},
            templateUrl: "/blog/comment/create.html",
            controller: "CommentCreateController as vm"
        })
        .state('comment.edit', {
            url: "/edit/:id",
            templateUrl: "/blog/comment/edit.html",
            controller: "CommentEditController as vm"
        })
        .state('comment.show', {
            url: "/show/:id",
            templateUrl: "/blog/comment/show.html",
            controller: "CommentShowController as vm"
        });
}
