package com.dat076hage.hage.auth;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

/**
 *
 * This will call Twitter for authentication (login)
 * and get the keys
 *
 * NOTE: To logout just invalidate the session
 *
 * @author hajo
 */
@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AuthServlet.class.getSimpleName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.log(Level.INFO, "*** AuthServlet");
        //Create an instance of SocialAuthConfgi object
        SocialAuthConfig config = SocialAuthConfig.getDefault();
        LOG.log(Level.INFO, "*** Auth got config");
        try {
            //load configuration. By default load the configuration from oauth_consumer.properties. 
            //You can also pass input stream, properties object or properties file name.
            config.load();
            LOG.log(Level.INFO, "*** Auth config loaded");
            //Create an instance of SocialAuthManager and set config
            SocialAuthManager manager = new SocialAuthManager();
            LOG.log(Level.INFO, "*** Auth manager{0}", manager);
            manager.setSocialAuthConfig(config);

            // URL of YOUR application which will be called (from twitter) after authentication        
            // MUST have localhost else session lost. See web for "naked hosts" (no domain)
            String successUrl = "http://localhost:8080/rest_oauth/callback";

            // Get Provider URL to which you should redirect for authentication.
            // id can have values "facebook", "twitter", "yahoo" etc. or the OpenID URL
            // This iwill issue a redirect!!
            String url = manager.getAuthenticationUrl("twitter", successUrl);

            // Store temporarily in session
            HttpSession session = request.getSession();
            session.setAttribute("authManager", manager);

            // Send to authentication
            response.sendRedirect(url);
                      
        } catch (Exception e) {
            e.printStackTrace();  // TODO 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
