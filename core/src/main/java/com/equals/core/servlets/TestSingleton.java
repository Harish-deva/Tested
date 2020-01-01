package com.equals.core.servlets;

import com.equals.core.enums.Synchronization;
import com.equals.core.utils.SessionHandler;
import org.apache.commons.lang3.EnumUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Objects;

@Component(service = Servlet.class, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.extensions=json", "sling.servlet.paths=/bin/global/testSession"})
@ServiceDescription("Verify Session created")
public class TestSingleton extends SlingSafeMethodsServlet {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
        log.info(" Request ID :: {} ", req.getUserPrincipal().getName());
        log.info(" Session ID :: {} ", SessionHandler.getSession(resourceResolverFactory).getUserID());
    }
}
