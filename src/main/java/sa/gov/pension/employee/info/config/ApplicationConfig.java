package sa.gov.pension.employee.info.config;

import io.quarkus.runtime.Startup;
import io.quarkus.vault.VaultKVSecretEngine;
import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;
import sa.gov.pension.profile.logging.db.ServiceInfoThreadLocal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static sa.gov.pension.profile.logging.LoggingUtil.destructAllLoggers;
import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.EMP_INFO_LOGGER_NAME;
import static sa.gov.pension.profile.logging.db.connection.constants.DBLoggerConstants.SECRET_PATH;
import static sa.gov.pension.profile.logging.db.connection.constants.DBLoggerConstants.loadDbLoggerConfig;

/**
 * @author Ahmed Anwar
 */
@Startup
@ApplicationScoped
public class ApplicationConfig {
    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(EMP_INFO_LOGGER_NAME);

    @Inject
    VaultKVSecretEngine vault;

    @PostConstruct
    public void init() {
        LOGGER.logInfoMessage("Initializing " + EMP_INFO_LOGGER_NAME + " app", getStackInfo());
        ServiceInfoThreadLocal.setAppName(EMP_INFO_LOGGER_NAME);
        try {
            loadDbLoggerConfig(vault.readSecret(SECRET_PATH));
        } catch (Exception e) {
            LOGGER.logErrorMessage(e.getMessage(), e, getStackInfo());
        }
    }

    @PreDestroy
    public void destruct() {
        LOGGER.logInfoMessage("Destroying " + EMP_INFO_LOGGER_NAME + " app loggers", getStackInfo());
        destructAllLoggers();
    }
}
