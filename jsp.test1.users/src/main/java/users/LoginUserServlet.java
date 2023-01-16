package users;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import test.TestUsersSingleton;

/**
 * Servlet implementation class LoginUserServlet
 */
@WebServlet("/LoginServlet")
public class LoginUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.out.println("Email: " + email + " Password: " + password);
		boolean login = false;
		
		try {
			login = UserService.getIstance().login(email, password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(login == true) {
			request.getRequestDispatcher("./users.jsp").forward(request, response);
		}
		else {
			response.getWriter().append("<html><body> <h2>Email or password are incorrect<h2>");
			response.getWriter().append("<a href=\"login.jsp\"> try again </a> ");
		}
		
	}

}
