//= wrapped

angular
    .module("blog.comment")
    .controller("CommentCreateController", CommentCreateController);

function CommentCreateController(Comment, $state, $stateParams, Post) {

    var vm = this;
    vm.postList = Post.list();
    vm.comment = new Comment();
    
    if ($stateParams.postId) {
        vm.comment.post = {id: $stateParams.postId};
    }
    
    vm.saveComment = function() {
        vm.errors = undefined;
        vm.comment.$save({}, function() {
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
