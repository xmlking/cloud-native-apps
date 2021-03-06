package blog

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.*
import grails.converters.*
import org.springframework.security.access.prepost.PreAuthorize;

@Secured(['ROLE_ADMIN'])
//@PreAuthorize("permitAll")
class TodoController extends RestfulController {

    static responseFormats = ['json', 'xml']

    TodoController() {
        super(Todo)
    }

    def pending() {
        respond Todo.findAllByDone(false), view: 'index'
    }
}