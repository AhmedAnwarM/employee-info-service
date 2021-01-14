package sa.gov.pension.employee.info.config;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;

import static sa.gov.pension.profile.logging.LoggingUtil.getHostName;

/**
 * @author Ahmed Anwar
 */
@Liveness
@ApplicationScoped
public class HostnameHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        try {
            return HealthCheckResponse.builder().
                    name("hostname").
                    withData("hostname", getHostName()).
                    up().
                    build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HealthCheckResponse.up("Failed to get hostname");
    }
}
