package sa.gov.pension.employee.info.config.credentials;

import io.quarkus.arc.Unremovable;
import io.quarkus.credentials.CredentialsProvider;
import io.quarkus.vault.VaultKVSecretEngine;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.logging.Logger;

import static java.util.logging.Level.FINE;
import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;

@Unremovable
@ApplicationScoped
@Named("profile-db-credentials-provider")
public class DbCredentialsProvider implements CredentialsProvider {
    private static final Logger LOGGER = Logger.getLogger(DbCredentialsProvider.class.getName());
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
            LOGGER.log(FINE, "Got DB Credentials from vault for " + secret.get("user"), getStackInfo());
        }
        return secret;
    }
}
