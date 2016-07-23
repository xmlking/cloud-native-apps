@Grab("org.grails:gorm-mongodb-spring-boot:5.0.10.RELEASE")
import grails.persistence.*
import grails.mongodb.geo.*
import org.bson.types.ObjectId

import static org.springframework.http.HttpStatus.*
import static org.springframework.web.bind.annotation.RequestMethod.*

@Log
@RestController
class CityController {

    @RequestMapping(value="/", method =  GET)
    List index() {
        City.list().collect { [name: it.name] }
    }

    @RequestMapping(value="/near/{cityName}", method = GET)
    ResponseEntity near(@PathVariable String cityName) {
        def city = City.where { name == cityName }.find()
        if(city) {
            List<City> closest = City.findAllByLocationNear(city.location)
            return new ResponseEntity([name: closest[1].name], OK)
        }
        else {
            return new ResponseEntity(NOT_FOUND)
        }
    }
}

@Entity
class City {
    ObjectId id
    String name
    Point location

    static constraints = {
        name blank:false
        location nullable:false
    }

    static mapping = {
        location geoIndex:'2dsphere'
    }
}