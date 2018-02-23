/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gR
 */
@WebServlet(urlPatterns = {"/myserverServlet"})
public class myserverServlet extends HttpServlet {
    static ArrayList<MessageDTO> messages =new ArrayList<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet myserverServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet myserverServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String name=(String) session.getAttribute("Username");
        String message = request.getParameter("message");
        MessageDTO messageDTO=new MessageDTO();
           messageDTO.setMessage(message);
           messageDTO.setSender(name);
           messages.add(messageDTO);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
          Gson gson=new Gson();
          response.setContentType("json");
          PrintWriter out =response.getWriter();
           out.write(gson.toJson(messages).toString());
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
