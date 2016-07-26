//= wrapped

angular
    .module("blog.comment")
    .controller("CommentShowController", CommentShowController);

function CommentShowController(Comment, $stateParams, $state) {
    var vm = this;

    Comment.get({id: $stateParams.id}, function(data) {
        vm.comment = new Comment(data);
    }, function() {
        $state.go('comment.list');
    });

    vm.delete = function() {
        vm.comment.$delete(function() {
            $state.go('comment.list');
        }, function() {
            //on error
        });
    };

}
