//= wrapped

angular
    .module("blog.post")
    .controller("PostShowController", PostShowController);

function PostShowController(Post, $stateParams, $state) {
    var vm = this;

    Post.get({id: $stateParams.id}, function(data) {
        vm.post = new Post(data);
    }, function() {
        $state.go('post.list');
    });

    vm.delete = function() {
        vm.post.$delete(function() {
            $state.go('post.list');
        }, function() {
            //on error
        });
    };

}
