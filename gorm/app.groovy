@Grab("spring-cloud-starter-bus-kafka")
@Grab("spring-boot-starter-actuator")
@Grab("org.grails:gorm-hibernate4-spring-boot:5.0.10.RELEASE")
@Grab("com.h2database:h2:1.4.192")
import grails.persistence.*
import static org.springframework.web.bind.annotation.RequestMethod.*

@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableTransactionManagement
@RestController
@Log
class GormPerson {

    @Transactional
    @RequestMapping("/")
    String home() {
        def person = new Person(firstName: 'Sumanth', lastName: 'Chinthagunta')
        person.save()
        [message: 'Hello World']
    }

    @Transactional
    @RequestMapping("/get")
    String get() {
        Person.get(1)
    }

    @RequestMapping(value = '/persons/{personId}')
    @HystrixCommand(fallbackMethod = "defaultPerson")
    def findById(@PathVariable String personId) {
        log.info  personId
        Person.withTransaction {
            def p = Person.get(personId)
            if(p) {
                return [firstName: p.firstName, lastName: p.lastName]
            }
            throw new IllegalArgumentException("demo fallBack")
        }
    }

    def defaultPerson(String questionId) {

        if(questionId == "5") {
            throw new IllegalArgumentException("must fail")
        }
        Person.withTransaction {
            def p = Person.get(1)
            return [firstName: p.firstName, lastName: p.lastName]
        }
    }
}


@Entity
@com.fasterxml.jackson.annotation.JsonIgnoreProperties(["errors"])
//@Resource(uri="/persons")
class Person {
    String firstName
    String lastName
    Integer age = 18

    static constraints = {
        firstName blank:false
        lastName blank:false
        age min: 0, max: 200
    }
}

