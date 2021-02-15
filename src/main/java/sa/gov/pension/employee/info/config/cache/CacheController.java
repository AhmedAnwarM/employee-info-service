package sa.gov.pension.employee.info.config.cache;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import sa.gov.pension.profile.objects.common.cache.Cacheable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ahmed Anwar
 */
@ApplicationScoped
public class CacheController {

    @Inject
    @Remote("employee-info")
    RemoteCache<String, Cacheable> cache;
    RemoteCacheManager remoteCacheManager;

    @Inject
    CacheController(RemoteCacheManager remoteCacheManager) {
        this.remoteCacheManager = remoteCacheManager;
    }

    public boolean contains(String key) {
        return cache.containsKey(key);
    }

    public Cacheable getCacheValue(String key) {
        return cache.get(key);
    }

    public void addCacheValue(String key, Cacheable value) {
        if (key != null && !key.isEmpty() && value != null) {
            cache.put(key, value);
        }
    }

    public void clear() {
        cache.clear();
    }

    public List<Map.Entry<String, Cacheable>> getCacheValues() {
        return new ArrayList<>(cache.entrySet());
    }
}
