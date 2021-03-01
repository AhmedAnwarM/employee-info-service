package sa.gov.pension.employee.info.config;

import io.quarkus.runtime.Startup;
import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;
import sa.gov.pension.profile.logging.db.ServiceInfoThreadLocal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import static sa.gov.pension.profile.logging.LoggingUtil.destructAllLoggers;
import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.EMP_INFO_LOGGER_NAME;

/**
 * @author Ahmed Anwar
 */
@Startup
@ApplicationScoped
public class ApplicationConfig {

    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(EMP_INFO_LOGGER_NAME);

    @PostConstruct
    public void init() {
        LOGGER.logInfoMessage("Initializing " + EMP_INFO_LOGGER_NAME + " app", getStackInfo());
        ServiceInfoThreadLocal.setAppName(EMP_INFO_LOGGER_NAME);
    }

    @PreDestroy
    public void destruct() {
        LOGGER.logInfoMessage("Destroying " + EMP_INFO_LOGGER_NAME + " app loggers", getStackInfo());
        destructAllLoggers();
    }
}
