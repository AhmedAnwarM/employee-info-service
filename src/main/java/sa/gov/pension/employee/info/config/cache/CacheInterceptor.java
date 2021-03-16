package sa.gov.pension.employee.info.config.cache;

import sa.gov.pension.profile.logging.LoggingUtil;
import sa.gov.pension.profile.logging.ProfileLogger;
import sa.gov.pension.profile.objects.common.cache.Cacheable;
import sa.gov.pension.profile.objects.common.cache.Cached;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import static javax.interceptor.Interceptor.Priority.APPLICATION;
import static sa.gov.pension.profile.cache.util.CacheKeyGenerator.generateCacheKey;
import static sa.gov.pension.profile.logging.ProfileLogger.getStackInfo;
import static sa.gov.pension.profile.logging.config.LoggingConfigKeys.PROFILE_LOGGER_NAME;

/**
 * @author mghanem
 */
@Cached
@Interceptor
@Priority(APPLICATION)
public class CacheInterceptor {

    private static final ProfileLogger LOGGER = LoggingUtil.getLogger(PROFILE_LOGGER_NAME);

    @Inject
    CacheController cache;

    @AroundInvoke
    public Object aroundInvoke(InvocationContext context) throws Exception {
        String key = generateCacheKey(context.getMethod(), context.getParameters());
        Cacheable value = null;
        try {
            if (cache.contains(key)) {
                value = cache.getCacheValue(key);
                LOGGER.logDebugMessage("Return from cache: " + value, getStackInfo());
            }
        } catch (Exception e) {
            LOGGER.logErrorMessage(e.getMessage(), e, getStackInfo());
        }
        if (value == null)
            value = proceed(context, key);
        return value;
    }

    private Cacheable proceed(InvocationContext context, String key) throws Exception {
        Cacheable value = (Cacheable) context.proceed();
        LOGGER.logDebugMessage("Return from method: " + value, getStackInfo());
        if (value != null)
            cache.addCacheValue(key, value);
        return value;
    }
}
