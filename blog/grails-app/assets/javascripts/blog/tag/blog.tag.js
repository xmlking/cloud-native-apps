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

angular.module("blog.tag", ["ui.router", "ngResource", "blog.core"]).config(config);

function config($stateProvider) {
    $stateProvider
        .state('tag', {
            url: "/tag",
            abstract: true,
            template: "<div ui-view></div>"
        })
        .state('tag.list', {
            url: "",
            templateUrl: "/blog/tag/list.html",
            controller: "TagListController as vm"
        })
        .state('tag.create', {
            url: "/create",
            templateUrl: "/blog/tag/create.html",
            controller: "TagCreateController as vm"
        })
        .state('tag.edit', {
            url: "/edit/:id",
            templateUrl: "/blog/tag/edit.html",
            controller: "TagEditController as vm"
        })
        .state('tag.show', {
            url: "/show/:id",
            templateUrl: "/blog/tag/show.html",
            controller: "TagShowController as vm"
        });
}
