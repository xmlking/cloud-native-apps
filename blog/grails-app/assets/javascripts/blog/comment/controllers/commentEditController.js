//= wrapped

angular
    .module("blog.comment")
    .controller("CommentEditController", CommentEditController);

function CommentEditController(Comment, $stateParams, $state, Post) {
    var vm = this;

    vm.postList = Post.list();

    Comment.get({id: $stateParams.id}, function(data) {
        vm.comment = new Comment(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve comment with ID " + $stateParams.id}];
    });

    vm.updateComment = function() {
        vm.errors = undefined;
        vm.comment.$update(function() {
            $state.go('comment.show', {id: vm.comment.id});
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
