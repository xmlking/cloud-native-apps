//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /blog/core/blog.core
//= require /blog/comment/blog.comment
//= require /blog/tag/blog.tag
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("blog.post", [
    "ui.router",
    "ngResource",
    "blog.core",
    "blog.comment",
    "blog.tag"
]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('post', {
            url: "/post",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('post.list', {
            url: "",
            templateUrl: "/blog/post/list.html",
            controller: "PostListController as vm"
        })
        .state('post.create', {
            url: "/create",
            templateUrl: "/blog/post/create.html",
            controller: "PostCreateController as vm"
        })
        .state('post.edit', {
            url: "/edit/:id",
            templateUrl: "/blog/post/edit.html",
            controller: "PostEditController as vm"
        })
        .state('post.show', {
            url: "/show/:id",
            templateUrl: "/blog/post/show.html",
            controller: "PostShowController as vm"
        });
}
