package sa.gov.pension.employee.info.support;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.openapi.annotations.Operation;
import sa.gov.pension.employee.info.config.cache.CacheController;
import sa.gov.pension.profile.logging.config.LoggingConfigUtil;
import sa.gov.pension.profile.objects.common.cache.ClearCacheResponse;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static sa.gov.pension.profile.logging.LoggingUtil.getHostName;

/**
 * @author Ahmed Anwar
 */
@Path("/support")
@Produces(APPLICATION_JSON)
public class SupportResources {
    public static final String SECRET_HEADER_KEY = "X-REQUESTER";
    public static final String SECRET_HEADER_VALUE = "secret-requester";

    @Inject
    CacheController cache;

    @DELETE
    @Path("/cache")
    @Operation(hidden = true)
    public Response clearCache() {
        cache.clear();
        LoggingConfigUtil.clearCache();
        return Response.ok(new ClearCacheResponse(getHostName())).
                build();
    }

    @GET
    @Path("/config/{key}")
    @Operation(hidden = true)
    public Response getConfigValue(@PathParam("key") String key, @Context HttpHeaders headers) {
        if (SECRET_HEADER_VALUE.equals(headers.getHeaderString(SECRET_HEADER_KEY)))
            return Response.ok(ConfigProvider.getConfig().getValue(key, String.class)).
                    build();
        else
            return Response.status(NOT_FOUND).build();
    }

    @GET
    @Path("/cache")
    @Operation(hidden = true)
    public Response getCacheValues(@Context HttpHeaders headers) {
        if (SECRET_HEADER_VALUE.equals(headers.getHeaderString(SECRET_HEADER_KEY)))
            return Response.ok(cache.getCacheValues()).
                    build();
        else
            return Response.status(NOT_FOUND).build();
    }

    @GET
    @Path("/cache/{key}")
    @Operation(hidden = true)
    public Response getCacheValue(@PathParam("key") String key, @Context HttpHeaders headers) {
        if (SECRET_HEADER_VALUE.equals(headers.getHeaderString(SECRET_HEADER_KEY)))
            return Response.ok(cache.getCacheValue(key)).
                    build();
        else
            return Response.status(NOT_FOUND).build();
    }
}
