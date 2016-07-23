import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
        Person.withTransaction {
            new Person(firstName: "John", lastName: "Johnson", age: 33).save(flush: true, failOnError: true)
            new Person(firstName: "Homer", lastName: "Simpson", age: 38).save(flush: true, failOnError: true)
        }
    }

}