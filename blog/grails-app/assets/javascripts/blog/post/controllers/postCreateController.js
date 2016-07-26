//= wrapped

angular
    .module("blog.post")
    .controller("PostCreateController", PostCreateController);

function PostCreateController(Post, $state, Comment, Tag) {

    var vm = this;
    vm.commentList = Comment.list();
    vm.tagList = Tag.list();
    vm.post = new Post();
    
    vm.savePost = function() {
        vm.errors = undefined;
        vm.post.$save({}, function() {
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
