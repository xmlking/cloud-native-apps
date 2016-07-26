//= wrapped

angular
    .module("blog.post")
    .controller("PostListController", PostListController);

function PostListController(Post) {
    var vm = this;

    var max = 10, offset = 0;

    Post.list({max: max, offset: offset}, function(data) {
        vm.postList = data;
    });
}
