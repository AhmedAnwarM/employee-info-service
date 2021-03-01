package sa.gov.pension.employee.info.config.mapper;

import sa.gov.pension.employee.info.inquiry.entity.exceptions.NinNotFoundException;
import sa.gov.pension.profile.objects.errorcodes.constants.Providers;
import sa.gov.pension.profile.objects.errorcodes.mapper.ResponseCodeEntity;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static sa.gov.pension.profile.objects.errorcodes.constants.ResponseCodes.CODE_BAD_REQUEST;
import static sa.gov.pension.profile.objects.errorcodes.constants.ResponseCodes.VALUE_BAD_REQUEST;

@Provider
public class NinNotFoundExceptionMapper implements ExceptionMapper<NinNotFoundException> {

    @Override
    public Response toResponse(NinNotFoundException exception) {
        return Response.status(BAD_REQUEST).
                entity(new ResponseCodeEntity(CODE_BAD_REQUEST,
                        VALUE_BAD_REQUEST + "Error: No NIN found for given ppa ID: " + exception.getPpaId(),
                        Providers.PROFILE)).
                build();
    }
}
