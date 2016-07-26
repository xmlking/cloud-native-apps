//= wrapped

angular
    .module("blog.tag")
    .controller("TagEditController", TagEditController);

function TagEditController(Tag, $stateParams, $state) {
    var vm = this;

    

    Tag.get({id: $stateParams.id}, function(data) {
        vm.tag = new Tag(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve tag with ID " + $stateParams.id}];
    });

    vm.updateTag = function() {
        vm.errors = undefined;
        vm.tag.$update(function() {
            $state.go('tag.show', {id: vm.tag.id});
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
