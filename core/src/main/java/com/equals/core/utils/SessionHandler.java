package com.equals.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SessionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static ResourceResolver resourceResolver;

    private static Session session;

    private SessionHandler(ResourceResolverFactory resolverFactory){
        Map<String, Object> param = new HashMap<>();
        if (StringUtils.isNotBlank(Constants.DATA_SERVICE_USER)) {
            param.put(ResourceResolverFactory.SUBSERVICE, Constants.DATA_SERVICE_USER);
        }
        try {
            resourceResolver = resolverFactory.getServiceResourceResolver(param);
        } catch (LoginException e) {
            log.error("Error in servce user : {} ", e);
        }
    }

    public static ResourceResolver getResolver(ResourceResolverFactory resolverFactory) {
            if (Objects.isNull(resourceResolver)){
                new SessionHandler(resolverFactory);
            }
            return Objects.requireNonNull(resourceResolver,"Please try again later as the session object couldnt be retrieved");
    }

    public static Session getSession(ResourceResolverFactory resolverFactory) {
        if (Objects.isNull(session)){
            new SessionHandler(resolverFactory);
            session =  Objects.requireNonNull(resourceResolver,"Please try again later as the session object couldnt be retrieved").adaptTo(Session.class);
        }
        return session;
    }

    public static void signout() {
           if(Objects.nonNull(session) && session.isLive()){
               session.logout();
           }
           if(Objects.nonNull(resourceResolver) &&resourceResolver.isLive()){
               resourceResolver.close();
           }
    }
}
