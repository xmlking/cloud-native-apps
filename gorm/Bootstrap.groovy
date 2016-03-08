//import org.springframework.context.ApplicationListener
//import org.springframework.context.event.ContextRefreshedEvent
//import org.springframework.stereotype.Component

@Log
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private boolean initialized = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initialized) {
            return;
        }
        initialized = true;
        log.info("Bootstrap")
        bootstrap()
    }

    public static void bootstrap() {
        Person.withTransaction {
            new Person(firstName: "John", lastName: "Johnson", age: 33).save(flush: true, failOnError: true)
            new Person(firstName: "Homer", lastName: "Simpson", age: 38).save(flush: true, failOnError: true)
        }
    }
}