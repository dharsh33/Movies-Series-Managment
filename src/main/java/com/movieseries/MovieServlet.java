package com.movieseries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import model.Movie;

@WebServlet("/MovieServlet") // This matches the exact fetch('MovieServlet') path in your script.js!
public class MovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MovieDAO movieDAO = new MovieDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            // 1. Read the incoming frontend data package
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            
            String jsonData = buffer.toString();
            System.out.println("Data package received from frontend: " + jsonData);
            
            // 2. Simple manual JSON parser to extract title and genre strings
            String title = "";
            String genre = "";
            
            if (jsonData.contains("\"title\":\"")) {
                int start = jsonData.indexOf("\"title\":\"") + 9;
                int end = jsonData.indexOf("\"", start);
                title = jsonData.substring(start, end);
            }
            if (jsonData.contains("\"genre\":\"")) {
                int start = jsonData.indexOf("\"genre\":\"") + 9;
                int end = jsonData.indexOf("\"", start);
                genre = jsonData.substring(start, end);
            }
            
            // 3. Populate your Model object structures
            Movie newMovie = new Movie();
            newMovie.setTitle(title);
            newMovie.setGenre(genre);
            
            // 4. Save to the database using the internal DAO system mapping lines
            boolean success = movieDAO.addMovie(newMovie); 
            
            // 5. Send status confirmation response message map back to user browser
            if (success) {
                out.print("{\"status\":\"success\", \"message\":\"Movie added successfully!\"}");
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print("{\"status\":\"error\", \"message\":\"Database insertion failed.\"}");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        } finally {
            out.flush();
        }
    }
}