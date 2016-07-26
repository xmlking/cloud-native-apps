//= wrapped

angular
    .module("blog.tag")
    .controller("TagShowController", TagShowController);

function TagShowController(Tag, $stateParams, $state) {
    var vm = this;

    Tag.get({id: $stateParams.id}, function(data) {
        vm.tag = new Tag(data);
    }, function() {
        $state.go('tag.list');
    });

    vm.delete = function() {
        vm.tag.$delete(function() {
            $state.go('tag.list');
        }, function() {
            //on error
        });
    };

}
