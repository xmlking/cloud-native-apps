@Grab("spring-cloud-starter-bus-amqp")
@Grab("spring-boot-starter-actuator")
@Grab("org.grails:gorm-hibernate4-spring-boot:5.0.0.RC3")
@Grab("com.h2database:h2:1.4.190")
import grails.persistence.*
@EnableDiscoveryClient
@EnableTransactionManagement
@RestController
class Example {

    @Transactional
    @RequestMapping("/")
    String home() {
        def person = new Person(firstName:'Sumanth', lastName:'Chinthagunta')
        person.save()
        [message: 'Hello World']
    }

    @Transactional
    @RequestMapping("/get")
    String get() {
        def person = Person.get(1)
        [person: "$person.firstName $person.lastName"]
    }

}

@Entity
//@Resource(uri="/persons")
class Person {
    String firstName
    String lastName

    static constraints = {
        firstName blank:false
        lastName blank:false
    }
}