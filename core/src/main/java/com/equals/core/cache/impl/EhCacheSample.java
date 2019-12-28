package com.equals.core.cache.impl;

import com.equals.core.cache.EhCacheService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EhCacheSample implements EhCacheService {

    private static final long serialVersionUID = 1L;
    transient Logger log = LoggerFactory.getLogger(this.getClass());

    Cache cache = null;

    @Override
    public void useCache() {

        CacheManager cm = CacheManager.getInstance();
        if (!cm.cacheExists("cache")) {
            cm.addCache("cache");
        }
        cache = cm.getCache("cache");

        if(cache.isKeyInCache("1") ){
            Element ele = cache.get("1");
            if (ele == null) {
                log.info("The cached content seems to have been expired ! Hence updating newly !!");
                cache.put(new Element("1", "Jan"));
                cache.getCacheConfiguration().setTimeToLiveSeconds(60);
            }else{
                String output =  ele.getObjectValue().toString();
                log.info("**** Value {} for key 1 existing on the cache *****", output);
            }
        }else {
            log.info("**** Create/Reset the cache *****");
            cache.put(new Element("1", "Jan"));
            cache.getCacheConfiguration().setTimeToLiveSeconds(60);
        }
    }
}
