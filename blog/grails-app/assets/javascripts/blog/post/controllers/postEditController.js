//= wrapped

angular
    .module("blog.post")
    .controller("PostEditController", PostEditController);

function PostEditController(Post, $stateParams, $state, Comment, Tag) {
    var vm = this;

    vm.commentList = Comment.list();
    vm.tagList = Tag.list();

    Post.get({id: $stateParams.id}, function(data) {
        vm.post = new Post(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve post with ID " + $stateParams.id}];
    });

    vm.updatePost = function() {
        vm.errors = undefined;
        vm.post.$update(function() {
            $state.go('post.show', {id: vm.post.id});
        }, function(response) {
            var data = response.data;
            if (data.hasOwnProperty('message')) {
                vm.errors = [data];
            } else {
                vm.errors = data._embedded.errors;
            }
        });
    };
}
