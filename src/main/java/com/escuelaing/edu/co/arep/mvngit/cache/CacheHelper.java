package com.escuelaing.edu.co.arep.mvngit.cache;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Expirations;

import com.escuelaing.edu.co.arep.mvngit.facade.FacadeAction;

public class CacheHelper {

    private CacheManager cacheManager;
    private Cache<String, FacadeAction> facadeActionCache;

    public CacheHelper() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        facadeActionCache = cacheManager.createCache("facadeActionCache", CacheConfigurationBuilder
        		.newCacheConfigurationBuilder(String.class, FacadeAction.class, ResourcePoolsBuilder.heap(20))
        		.withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(5))).build());
        		
    }

    public Cache<String, FacadeAction> getFacadeActionCache() {
        return facadeActionCache;
    }

    public Cache<String, FacadeAction> getFacadeActionCacheFromCacheManager() {
        return cacheManager.getCache("facadeActionCache", String.class, FacadeAction.class);
    }

}