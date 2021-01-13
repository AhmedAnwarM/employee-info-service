package sa.gov.pension.employee.info.jaxrs.config;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(INTERNAL_SERVER_ERROR).
                entity("{\"errorMessage\": \"" + exception.getMessage() + "\","
                        + "\"status\": \"Error: General Error\"}").
                build();
    }
}
