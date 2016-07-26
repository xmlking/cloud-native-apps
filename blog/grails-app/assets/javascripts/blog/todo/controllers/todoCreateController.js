//= wrapped

angular
    .module("blog.todo")
    .controller("TodoCreateController", TodoCreateController);

function TodoCreateController(Todo, $state) {

    var vm = this;
    
    vm.todo = new Todo();
    
    vm.saveTodo = function() {
        vm.errors = undefined;
        vm.todo.$save({}, function() {
            $state.go('todo.show', {id: vm.todo.id});
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
