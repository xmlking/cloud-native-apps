//= wrapped

angular
    .module("blog.todo")
    .controller("TodoEditController", TodoEditController);

function TodoEditController(Todo, $stateParams, $state) {
    var vm = this;

    

    Todo.get({id: $stateParams.id}, function(data) {
        vm.todo = new Todo(data);
    }, function() {
        vm.errors = [{message: "Could not retrieve todo with ID " + $stateParams.id}];
    });

    vm.updateTodo = function() {
        vm.errors = undefined;
        vm.todo.$update(function() {
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
