//= wrapped

angular
    .module("blog.tag")
    .controller("TagCreateController", TagCreateController);

function TagCreateController(Tag, $state) {

    var vm = this;
    
    vm.tag = new Tag();
    
    vm.saveTag = function() {
        vm.errors = undefined;
        vm.tag.$save({}, function() {
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
