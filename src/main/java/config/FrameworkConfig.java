package config;
import org.aeonbits.owner.Config;

@Config.Sources(value = "file:${user.dir}/src/test/resources/config/config.properties")
public interface FrameworkConfig extends Config {

    String BASE_URL();
    String AUTH_ENDPOINT();
    String BOOKING_ENDPOINT();
    String PING_ENDPOINT();
}
