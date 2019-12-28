package com.equals.core.servlets;

import com.equals.core.cache.EhCacheService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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

@Component(service = Servlet.class, property = {"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.extensions=json", "sling.servlet.paths=/bin/global/ehcache"})
@ServiceDescription("Verify Cache")
public class TestServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Reference
    private EhCacheService ehCacheService;

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp) throws ServletException, IOException {
            ehCacheService.useCache();
    }

}
