//= wrapped

angular
    .module("blog.tag")
    .controller("TagListController", TagListController);

function TagListController(Tag) {
    var vm = this;

    var max = 10, offset = 0;

    Tag.list({max: max, offset: offset}, function(data) {
        vm.tagList = data;
    });
}
