import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import com.mongodb.BasicDBObject
import grails.persistence.*
import grails.mongodb.geo.*

@Log
@Component
public class BootstrapData implements ApplicationListener<ApplicationReadyEvent> {

    private boolean initialized = false;

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        // don't bootstrap multiple times.
        if (initialized) {
            return;
        }
        initialized = true;
        log.info("in BootstrapData")
        bootstrap()

    }

    public static void bootstrap() {
        City.withTransaction{
            City.collection.remove(new BasicDBObject())
            City.saveAll( [ new City(name:"London", location: Point.valueOf( [-0.125487, 51.508515] ) ),
                            new City(name:"Paris", location: Point.valueOf( [2.352222, 48.856614] ) ),
                            new City(name:"New York", location: Point.valueOf( [-74.005973, 40.714353] ) ),
                            new City(name:"San Francisco", location: Point.valueOf( [-122.419416, 37.774929] ) ) ] )
        }
    }

}