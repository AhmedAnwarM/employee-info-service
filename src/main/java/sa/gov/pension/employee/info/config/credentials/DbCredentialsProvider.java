package sa.gov.pension.employee.info.config.credentials;

import io.quarkus.arc.Unremovable;
import io.quarkus.credentials.CredentialsProvider;
import io.quarkus.vault.VaultKVSecretEngine;
import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.EMP_INFO_LOGGER_NAME;

@Unremovable
@ApplicationScoped
@Named("profile-db-credentials-provider")
public class DbCredentialsProvider implements CredentialsProvider {
    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(EMP_INFO_LOGGER_NAME);
    private static final String SECRET_PATH = "profile/db/credentials";

    @Inject
    VaultKVSecretEngine vault;
    private Map<String, String> secret;

    @Override
    public Map<String, String> getCredentials(String credentialsProviderName) {
        if (secret == null ||
            secret.get("password") == null ||
            secret.get("user") == null) {
            this.secret = vault.readSecret(SECRET_PATH);
            LOGGER.logDebugMessage("Got DB Credentials from vault for " + secret.get("user"), getStackInfo());
        }
        return secret;
    }
}
