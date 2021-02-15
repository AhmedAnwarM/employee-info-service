package sa.gov.pension.employee.info.inquiry.boundary;

import sa.gov.pension.employee.info.inquiry.control.EmployeeInfoRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author Ahmed Anwar
 */
@Path("/employee-info")
public class EmployeeInfoResources {

    @Inject
    EmployeeInfoRepository repo;

    @GET
    @Path("/nin/{ppaId}")
    @Produces(APPLICATION_JSON)
    public Response getEmployeeNin(@PathParam("ppaId") String ppaId) throws Exception {
        return Response.ok(repo.getEmployeeNin(ppaId)).
                build();
    }
}
