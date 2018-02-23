
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
import javax.websocket.Session;
@WebServlet(urlPatterns = {"/UsercheckServlet"})
public class UsercheckServlet extends HttpServlet {

    static ArrayList<UserDTO> Users = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsercheckServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsercheckServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.sendRedirect("ChatRoom.html");
     String user = request.getParameter("User");
     PrintWriter out = response.getWriter();
     
        if (user==null) {
            String Email = request.getParameter("Email");
            String password = request.getParameter("Password");
            if(Users.size()>0)
            {
            for(UserDTO myUser: Users)
            {// Email only must be unique
              if(myUser.getEmail().equals(Email))
              {
                  HttpSession usersession = request.getSession(true);
                  usersession.setAttribute("Username",myUser.getUserName());
                  response.sendRedirect("ChatRoom.html");
              }
              else
              {
              response.sendRedirect("Signup.html");
              
              }
              
            }
            }
            else
            {
            response.sendRedirect("Signup.html");
            }

        } else  {
            String Email = request.getParameter("Email");
            String password = request.getParameter("Password");
            String Username = request.getParameter("User");
            if(Users.size()>0)
            {
                    for(UserDTO myUser: Users)
                {// Email only must be unique
                  if(!(myUser.getEmail().equals(Email)))
                  {
                      UserDTO NewUser=new UserDTO();
                      NewUser.setEmail(Email);
                      NewUser.setPassword(password);
                      NewUser.setUserName(Username);
                      NewUser.setStatus("online");
                      Users.add(NewUser);
                      response.sendRedirect("ChatRoom.html");
                  }
                  else
                  {
                  response.sendRedirect("Login.html");

                  }

                }
            }
            else
            {
                      UserDTO NewUser=new UserDTO();
                      NewUser.setEmail(Email);
                      NewUser.setPassword(password);
                      NewUser.setUserName(Username);
                      NewUser.setStatus("online");
                      Users.add(NewUser);
                      HttpSession usersession = request.getSession(true);
                      usersession.setAttribute("Username",NewUser.getUserName());
                      response.sendRedirect("Login.html");
            
            }
            
        }
        
        


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Gson gson=new Gson();
          response.setContentType("json");
          PrintWriter out =response.getWriter();
           out.write(gson.toJson(Users).toString());
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
