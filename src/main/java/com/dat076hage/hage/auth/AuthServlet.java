package com.dat076hage.hage.auth;


import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

/**
 * Third party oauth servlet
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String callbackUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/callback";
        SocialAuthConfig config = SocialAuthConfig.getDefault();
        SocialAuthManager manager = new SocialAuthManager();
        String url;

        try {
            // If an environment.properties exist, use it
            try {
                config.load("environment.properties");
            } catch( FileNotFoundException e) {
                config.load("default.properties"); 
            }
            
            manager.setSocialAuthConfig(config);
            url = manager.getAuthenticationUrl("twitter", callbackUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Store temporarily in session
        request.getSession().setAttribute("authManager", manager);
        response.sendRedirect(url);
    }
}
