//= wrapped
//= require /angular/angular 
//= require /angular/angular-ui-router
//= require /angular/angular-resource
//= require /blog/core/blog.core
//= require_self
//= require_tree services
//= require_tree controllers
//= require_tree directives
//= require_tree domain
//= require_tree templates

angular.module("blog.todo", ["ui.router", "ngResource", "blog.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('todo', {
            url: "/todo",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('todo.list', {
            url: "",
            templateUrl: "/blog/todo/list.html",
            controller: "TodoListController as vm"
        })
        .state('todo.create', {
            url: "/create",
            templateUrl: "/blog/todo/create.html",
            controller: "TodoCreateController as vm"
        })
        .state('todo.edit', {
            url: "/edit/:id",
            templateUrl: "/blog/todo/edit.html",
            controller: "TodoEditController as vm"
        })
        .state('todo.show', {
            url: "/show/:id",
            templateUrl: "/blog/todo/show.html",
            controller: "TodoShowController as vm"
        });
}
