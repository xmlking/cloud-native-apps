//= wrapped

angular
    .module("blog.comment")
    .controller("CommentListController", CommentListController);

function CommentListController(Comment) {
    var vm = this;

    var max = 10, offset = 0;

    Comment.list({max: max, offset: offset}, function(data) {
        vm.commentList = data;
    });
}
