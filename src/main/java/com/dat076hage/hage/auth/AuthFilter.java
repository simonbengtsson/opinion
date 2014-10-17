package com.dat076hage.hage.auth;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.AccessGrant;

/**
 * Protects sensitive routes
 */
@Provider
public class AuthFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(AuthFilter.class.getName());

    @Context
    SecurityContext sCtx;
    
    @Override
    public void filter(ContainerRequestContext rCtx) {
    
        /**
        LOG.log(Level.INFO, "*** AuthFilter");
        Map<String, Cookie> cookies = rCtx.getCookies();
        Cookie c = cookies.get("key");
        if (c == null) {
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        AccessGrant ag = new AccessGrant();
        ag.setKey(c.getValue());
        ag.setProviderId("twitter");

        try {
            SocialAuthConfig config = SocialAuthConfig.getDefault();
            config.load();
            SocialAuthManager manager = new SocialAuthManager();
            manager.setSocialAuthConfig(config);
            manager.connect(ag);
            LOG.log(Level.INFO, "** Filter auth ok");

        } catch (Exception ex) {
            throw new WebApplicationException(Status.UNAUTHORIZED);
        }
        **/
    }

}
