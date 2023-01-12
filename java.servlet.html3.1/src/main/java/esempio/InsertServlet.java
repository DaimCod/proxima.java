package esempio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/Insert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email"); //deve coincidere con il nome dei parametri in html
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
		String imgpath = request.getParameter("imgpath");
		String role = request.getParameter("role");
		String hrnote = request.getParameter("hrnote");
		String technicalnote = request.getParameter("technicalnote");
		String enabled = request.getParameter("enabled");
		
		response.getWriter().append("<html><body>Ho ricevuto correttamente i dati<br>");
		
		String finalResult = "";
		
		try {
			
			int result = DatabaseManagementSingleton.getIstance()
									.insertIntoDB(email, firstName, lastName, birthday, imgpath,role,hrnote,technicalnote,enabled);
			
			response.getWriter().append("Ho ricevuto correttamente i dati<br>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		response.getWriter().append("<a href=\"./List\"> Clicca qui per visualizzare </a>");
		
		finalResult += "</body></html>";
		
		response.getWriter().append(finalResult);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
