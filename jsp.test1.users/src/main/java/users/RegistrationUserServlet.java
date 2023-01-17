package users;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

/**
 * Servlet implementation class RegistrationUserServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String email = request.getParameter("email");
		 String password  = request.getParameter("password");
		 String firstname  = request.getParameter("firstname");
		 String lastname  = request.getParameter("lastname");
		 String dateofbirth = request.getParameter("birthday");
		 
		 boolean signedUp = false;
		 try {
			signedUp = UserService.getIstance().insert(email,password,firstname,lastname,dateofbirth);
			System.out.println("Servlet Reg " + signedUp);
		 } catch (Exception e) {
			e.printStackTrace();
		}
		 
		 if(signedUp) {
			 response.getWriter().append("<body> <h2>Registration completed successfully. <a href=\"./login.jsp\">login HERE</a></h2>");
			
		 }
		 else {
			 response.getWriter().append("<body> <h2>Email already in use, please sign in <a href=\"./login.jsp\">HERE</a></h2>");
		 }
	}

}
