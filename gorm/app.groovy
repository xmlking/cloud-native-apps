@Grab("spring-cloud-starter-bus-amqp")
@Grab("spring-boot-starter-actuator")
@Grab("org.grails:gorm-hibernate4-spring-boot:5.0.0.RC3")
@Grab("com.h2database:h2:1.4.190")
import grails.persistence.*
import static java.util.Optional.ofNullable
import org.springframework.http.HttpStatus
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
        def p
        Person.withTransaction {
            p = Person.get(personId)
            if(p) {
                return [firstName: p.firstName, lastName: p.lastName]
            }
            throw new IllegalArgumentException("demo fallBack")
            //return new ResponseEntity( p , p ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
        }
    }

    def defaultPerson(String questionId) {
        Person.withTransaction {
            def p = Person.get(1)
            return [firstName: p.firstName, lastName: p.lastName]
        }
    }

//    @PostConstruct
//    void populatePeople() {
//        Person.withTransaction{
//            Person.saveAll( [ new Person(firstName:"sumo", lastName: "demo"),
//                            new Person(firstName:"sumo1", lastName: "demo1"),
//                            new Person(firstName:"sumo2", lastName: "demo2"),
//                            new Person(firstName:"sumo3", lastName: "demo3" ) ] )
//        }
//    }
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

