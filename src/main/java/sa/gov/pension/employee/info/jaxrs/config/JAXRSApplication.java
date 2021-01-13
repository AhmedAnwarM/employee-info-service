package sa.gov.pension.employee.info.jaxrs.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Ahmed Anwar
 */
@ApplicationPath("/api")
@OpenAPIDefinition(
        info = @Info(
                title = "PPA Employee Information API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Ahmed Anwar",
                        email = "mailto:9785@pension.gov.sa"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html")))
public class JAXRSApplication extends Application {
}
