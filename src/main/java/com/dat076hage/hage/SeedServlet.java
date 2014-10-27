/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.model.Comment;
import com.dat076hage.hage.model.GPS;
import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kimkling
 */
@WebServlet(name = "SeedServlet", urlPatterns = {"/seed-database"})
public class SeedServlet extends HttpServlet {

    @EJB
    UserRegistry userReg;
    
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    @EJB
    PostRegistry postReg;
    
    @EJB
    CommentRegistry commentReg;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

        if(userReg.find("kim") == null){
            System.out.println("*** Seeding Database ***");
            
            User kim = new User("kim", "My name is Kim Kling", "", "", "", "Kim Kling");
            User simonB = new User("simonb", "My name is Simon Bengtsson", "", "", "", "Simon Bengtsson");
            User simonP = new User("simonp", "My name is Simon Planhage", "", "", "", "Simon Planhage");
            User caroline = new User("caroline", "My name is Caroline Kabat", "", "", "", "Caroline Kabat");

            /*simonP.follow(kim);
            simonP.follow(caroline);
            simonP.follow(simonB);*/
            
            userReg.create(kim);
            userReg.create(simonB);
            userReg.create(caroline);
            userReg.create(simonP);
            
            List<String> tags = new ArrayList<>();
            tags.add("awesome");
            tags.add("hashtags");

            Post kimPost1 = new Post(kim, "This is my first, simple Post!");
            Post kimPost2 = new Post(kim, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
            Post kimPost3 = new Post(kim, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
            Post simonBPost1 = new Post(simonB, "This is my first, simple Post!");
            Post simonBPost2 = new Post(simonB, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
            Post simonBPost3 = new Post(simonB, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
            Post simonPPost1 = new Post(simonP, "This is my first, simple Post!");
            Post simonPPost2 = new Post(simonP, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
            Post simonPPost3 = new Post(simonP, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
            Post carolinePost1 = new Post(caroline, "This is my first, simple Post!");
            Post carolinePost2 = new Post(caroline, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
            Post carolinePost3 = new Post(caroline, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
            
            postReg.create(kimPost1);
            postReg.create(kimPost2);
            postReg.create(kimPost3);
            postReg.create(simonBPost1);
            postReg.create(simonBPost2);
            postReg.create(simonBPost3);
            postReg.create(simonPPost1);
            postReg.create(simonPPost2);
            postReg.create(simonPPost3);
            postReg.create(carolinePost1);
            postReg.create(carolinePost2);
            postReg.create(carolinePost3);
            
            Comment kimOnSimonBPost1 = new Comment(kim, simonBPost1, "This is a comment by kim on Post 1 by simonb.");
            Comment simonbOnSimonPPost2 = new Comment(simonB, simonPPost2, "This is a comment by simonb on Post 2 by simonp.");
            Comment simonpOnCarolinePost3 = new Comment(simonP, carolinePost3, "This is a comment by simonp on Post 3 by caroline.");
            Comment carolineOnSimonPPost2 = new Comment(caroline, simonPPost2, "This is a comment by caroline on Post 2 by simonp.");
            
            commentReg.create(kimOnSimonBPost1);
            commentReg.create(simonbOnSimonPPost2);
            commentReg.create(simonpOnCarolinePost3);
            commentReg.create(carolineOnSimonPPost2);
        }else{
            System.out.println("*** No Need To Seed Database ***");
        }

        
        

        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SeedServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Seeding complete!</h1>");
            out.println("</body>");
            out.println("</html>");
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
