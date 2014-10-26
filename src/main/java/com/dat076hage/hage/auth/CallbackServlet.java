package com.dat076hage.hage.auth;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.Tools;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.model.User;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.AccessGrant;
import org.brickred.socialauth.util.SocialAuthUtil;

/**
 * Servlet that is called by third-party oauth provider
 */
@WebServlet(name = "CallbackServlet", urlPatterns = {"/callback"})
public class CallbackServlet extends HttpServlet {

    @EJB
    UserRegistry userReg;

    @EJB
    ApiKeyRegistry apiKeyReg;

    private static final Logger LOG = Logger.getLogger(CallbackServlet.class.getSimpleName());

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Must use same manager as used for login
        SocialAuthManager manager = (SocialAuthManager) request.getSession().getAttribute("authManager");
        Map<String, String> paramsMap = SocialAuthUtil.getRequestParametersMap(request);

        AuthProvider provider;
        Profile profile;

        try {
            provider = manager.connect(paramsMap);
            profile = provider.getUserProfile();
        } catch (Exception ex) {
            response.sendRedirect("");
            return;
        }
        
        AccessGrant ag = provider.getAccessGrant();

        User user = getUser(profile, ag);
        ApiKey apiKey = new ApiKey(user);
        apiKeyReg.create(apiKey);

        response.getWriter().write(getResponseScript(apiKey.getKey()));

        request.getSession().invalidate();
    }
    
    private User getUser(Profile profile, AccessGrant ag) {
        String username = profile.getDisplayName();
        String name = profile.getFullName();
        String description = "I live in " + profile.getLocation();
        String picture = profile.getProfileImageURL();
        picture = picture.replaceAll("_normal", "_bigger");
        
        User user = userReg.find(username);
        if (user == null) {
            user = new User(username, description, "", ag.getKey(), picture, name);
            userReg.create(user);
        }
        
        return user;
    }

    private String getResponseScript(String apiKey) {
        return "<script>localStorage.setItem('authKey', '"
                + apiKey + "'); location.href = '" + Tools.URL_FOLDER + "'; </script>";
    }
}
