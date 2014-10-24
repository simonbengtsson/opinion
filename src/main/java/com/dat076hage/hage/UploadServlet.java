/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploads")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class UploadServlet extends HttpServlet {

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR = "uploads";

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String filename = "";
        Part part = request.getParts().iterator().next();
        filename = getFilename(part);
        saveFile(part.getInputStream(), savePath + File.separator + filename);

        if (filename.equals("")) {
            response.sendError(400);
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            response.setHeader("Content-Type", "application/json");
            out.println("{\"filename\": \"" + filename + "\"}");
        }
    }

    private void saveFile(InputStream is, String savePath) throws IOException {
        byte[] buffer = new byte[is.available()];
        is.read(buffer);
        OutputStream outStream = new FileOutputStream(new File(savePath));
        outStream.write(buffer);
    }

    private String getFilename(Part part) {
        Timestamp time = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        return time.getTime() + "-" + part.getSubmittedFileName();
    }
}
