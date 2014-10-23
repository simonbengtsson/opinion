package com.dat076hage.hage.auth;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.model.User;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
    
    @PersistenceContext(unitName="hage_pu")
    private EntityManager em;

    @EJB
    UserRegistry userReg;
    
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    private static final Logger LOG = Logger.getLogger(CallbackServlet.class.getSimpleName());

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOG.log(Level.INFO, "*** CallbackServlet");
        AccessGrant ag;
        String username = "";
        String description = "";
;
        try {
            // Must use same manager as used for login
            SocialAuthManager manager = (SocialAuthManager) request.getSession().getAttribute("authManager");
            Map<String, String> paramsMap = SocialAuthUtil.getRequestParametersMap(request);
            AuthProvider provider = manager.connect(paramsMap);
            Profile profile = provider.getUserProfile();
            username = profile.getFullName();
            description = profile.getLastName();
            System.out.println("*** Name " + username);
            LOG.log(Level.INFO, "*** Name " + username);
            ag = provider.getAccessGrant();
        } catch (Exception ex) {
            response.sendRedirect("");
            return;
        }
        
        if(!username.equals("")){
            User searchedUser = userReg.find(username);
            if(searchedUser == null){
                User user = new User(username, description, "", ag.getKey());
                em.persist(user);
                
                ApiKey apiKey = new ApiKey(user);
                
                String script = "<script>localStorage.setItem('authKey', '" + 
                        apiKey.getKey() + "'); location.href = '/Hage-DAT076'; </script>";
                response.getWriter().write(script);
            }else{
                
                ApiKey apiKey = new ApiKey(searchedUser);
                
                String script = "<script>localStorage.setItem('authKey', '" + 
                        apiKey.getKey() + "'); location.href = '/Hage-DAT076'; </script>";
                response.getWriter().write(script);
            }
        }else{
            response.getWriter().write("Not a valid username");
        }

        request.getSession().invalidate();
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
