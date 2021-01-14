package sa.gov.pension.employee.info.config.mapper;

import sa.gov.pension.employee.info.entity.exceptions.NinNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class NinNotFoundExceptionMapper implements ExceptionMapper<NinNotFoundException> {

    @Override
    public Response toResponse(NinNotFoundException exception) {
        return Response.status(BAD_REQUEST).
                entity("{\"errorMessage\": \"" + exception.getPpaId() + "\","
                        + "\"status\": \"Error: No NIN found for given ppa ID\"}").
                build();
    }
}
