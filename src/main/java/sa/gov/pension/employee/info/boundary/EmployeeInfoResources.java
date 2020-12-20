package sa.gov.pension.employee.info.boundary;

import sa.gov.pension.employee.info.control.EmployeeInfoRepository;
import sa.gov.pension.employee.info.entity.exceptions.NinNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * @author Ahmed Anwar
 */
@Path("/employee-info")
public class EmployeeInfoResources {

    @Inject
    EmployeeInfoRepository repo;

    @GET
    @Path("/nin/{ppaId}")
    @Produces(TEXT_PLAIN)
    public Response getEmployeeNin(@PathParam("ppaId") String ppaId) {
        try {
            return Response.ok(repo.getEmployeeNin(ppaId)).
                    build();
        } catch (NinNotFoundException e) {
            return Response.status(BAD_REQUEST).
                    entity(e.getMessage()).
                    build();
        } catch (Exception e) {
            return Response.status(INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).
                    build();
        }
    }
}
