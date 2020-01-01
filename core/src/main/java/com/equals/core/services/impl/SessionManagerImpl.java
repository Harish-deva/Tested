package com.equals.core.services.impl;

import com.equals.core.services.SessionManager;
import com.equals.core.utils.SessionHandler;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component
public class SessionManagerImpl implements SessionManager {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Activate
    public void signIn(){
        SessionHandler.getResolver(resourceResolverFactory);
    }

    @Deactivate
    public void signOut(){
        SessionHandler.signout();
    }

}
