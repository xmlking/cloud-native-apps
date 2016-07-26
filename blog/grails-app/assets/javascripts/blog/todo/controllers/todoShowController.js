//= wrapped

angular
    .module("blog.todo")
    .controller("TodoShowController", TodoShowController);

function TodoShowController(Todo, $stateParams, $state) {
    var vm = this;

    Todo.get({id: $stateParams.id}, function(data) {
        vm.todo = new Todo(data);
    }, function() {
        $state.go('todo.list');
    });

    vm.delete = function() {
        vm.todo.$delete(function() {
            $state.go('todo.list');
        }, function() {
            //on error
        });
    };

}
