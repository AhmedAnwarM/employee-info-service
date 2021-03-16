package sa.gov.pension.employee.info.config.cache;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;
import sa.gov.pension.profile.objects.common.cache.Cacheable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.EMP_INFO_LOGGER_NAME;

/**
 * @author Ahmed Anwar
 */
@ApplicationScoped
public class CacheController {
    public static final String CACHE_NAME = "employee-info";
    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(EMP_INFO_LOGGER_NAME);

    @Inject
    RemoteCacheManager remoteCacheManager;

    @Inject
    @Remote(CACHE_NAME)
    RemoteCache<String, Cacheable> cache;

    public boolean contains(String key) {
        if (remoteCacheManager.isStarted() && !remoteCacheManager.getCacheNames().contains(CACHE_NAME)) {
            LOGGER.logErrorMessage("Cache with name " + CACHE_NAME + " was not found", getStackInfo());
            return false;
        }
        return cache.containsKey(key);
    }

    public Cacheable getCacheValue(String key) {
        if (remoteCacheManager.isStarted() && !remoteCacheManager.getCacheNames().contains(CACHE_NAME)) {
            LOGGER.logErrorMessage("Cache with name " + CACHE_NAME + " was not found", getStackInfo());
            return null;
        }
        return cache.get(key);
    }

    public void addCacheValue(String key, Cacheable value) {
        if (remoteCacheManager.isStarted() && !remoteCacheManager.getCacheNames().contains(CACHE_NAME)) {
            LOGGER.logErrorMessage("Cache with name " + CACHE_NAME + " was not found", getStackInfo());
            return;
        }
        if (key != null && !key.isEmpty() && value != null) {
            cache.put(key, value);
        }
    }

    public List<Map.Entry<String, Cacheable>> getCacheValues() {
        if (remoteCacheManager.isStarted() && !remoteCacheManager.getCacheNames().contains(CACHE_NAME)) {
            LOGGER.logErrorMessage("Cache with name " + CACHE_NAME + " was not found", getStackInfo());
            return new ArrayList<>();
        }
        return new ArrayList<>(cache.entrySet());
    }

    public void clear() {
        if (remoteCacheManager.isStarted() && !remoteCacheManager.getCacheNames().contains(CACHE_NAME)) {
            LOGGER.logErrorMessage("Cache with name " + CACHE_NAME + " was not found", getStackInfo());
            return;
        }
        cache.clear();
    }
}
