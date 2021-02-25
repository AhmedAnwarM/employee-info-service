package sa.gov.pension.employee.info.config;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

import static sa.gov.pension.profile.logging.LoggingUtil.getHostName;

/**
 * @author Ahmed Anwar
 */
@Liveness
@ApplicationScoped
public class HostnameHealthCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.builder().
                name("hostname").
                withData("hostname", getHostName()).
                up().
                build();
    }
}
