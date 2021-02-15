package sa.gov.pension.employee.info.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;
import sa.gov.pension.profile.logging.db.ServiceInfoThreadLocal;
import sa.gov.pension.profile.logging.db.entity.EventCorrelationType;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

import static sa.gov.pension.profile.logging.LoggingUtil.GLOB_TRANS_ID;
import static sa.gov.pension.profile.logging.LoggingUtil.REQUEST_ID;
import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.EMP_INFO_LOGGER_NAME;
import static sa.gov.pension.profile.logging.config.LoggingConfigUtil.dbLoggingEnabled;

/**
 * @author Ahmed Anwar
 */
@Provider
public class RestLoggerFilter implements ContainerResponseFilter, ContainerRequestFilter {

    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(EMP_INFO_LOGGER_NAME);
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        MAPPER.registerModule(new JavaTimeModule());
    }

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String globalTransId = requestContext.getHeaderString(GLOB_TRANS_ID);
        String requestId = requestContext.getHeaderString(REQUEST_ID);
        if (globalTransId == null) {
            EventCorrelationType corr = ServiceInfoThreadLocal.getCorrelationInfo();
            globalTransId = corr.getGlobalTransactionId();
            requestId = corr.getLocalTransactionId();
            requestContext.getHeaders().putSingle(GLOB_TRANS_ID, globalTransId);
            requestContext.getHeaders().putSingle(REQUEST_ID, requestId);
        } else {
            ServiceInfoThreadLocal.set(requestId, globalTransId);
        }
        requestContext.setProperty(GLOB_TRANS_ID, globalTransId);
        requestContext.setProperty(REQUEST_ID, requestId);
        LOGGER.logDebugMessage(
                "Entering REST Service: \n" + requestContext.getMethod() + "\t" + requestContext.getUriInfo().getRequestUri().toString(),
                getStackInfo());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        EventCorrelationType correlationInfo = ServiceInfoThreadLocal.getCorrelationInfo();
        responseContext.getHeaders().putSingle(REQUEST_ID, correlationInfo.getLocalTransactionId());
        responseContext.getHeaders().putSingle(GLOB_TRANS_ID, correlationInfo.getGlobalTransactionId());
        LOGGER.logDebugMessage(
                "Return from REST Service: \n" + requestContext.getMethod() + "\t" + requestContext.getUriInfo().getRequestUri().toString()
                        + "\n" + MAPPER.writeValueAsString(responseContext.getEntity()), getStackInfo(), dbLoggingEnabled());
        ServiceInfoThreadLocal.remove();
    }
}
