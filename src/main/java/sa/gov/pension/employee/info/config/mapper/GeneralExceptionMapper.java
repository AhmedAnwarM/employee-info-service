package sa.gov.pension.employee.info.config.mapper;

import sa.gov.pension.profile.objects.errorcodes.constants.Providers;
import sa.gov.pension.profile.objects.errorcodes.mapper.ResponseCodeEntity;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static sa.gov.pension.profile.objects.errorcodes.constants.ResponseCodes.CODE_BAD_REQUEST;
import static sa.gov.pension.profile.objects.errorcodes.constants.ResponseCodes.VALUE_BAD_REQUEST;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return Response.status(INTERNAL_SERVER_ERROR).
                entity(new ResponseCodeEntity(CODE_BAD_REQUEST,
                        VALUE_BAD_REQUEST + "Error: General Error: " + exception.getMessage(),
                        Providers.PROFILE)).
                build();
    }
}
