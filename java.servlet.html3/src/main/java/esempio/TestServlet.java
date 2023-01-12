package esempio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/MessagesList")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Verifico input ricevuto da front end
		String userName = request.getParameter("userName"); //deve coincidere con il nome dei parametri in html
		String textMessage = request.getParameter("textMessage");
		String ldt = request.getParameter("datetime");
		
		System.out.println("userName: " + userName);
		System.out.println("textMessage: " + textMessage);
		
		response.getWriter().append("<html><body>Ho ricevuto correttamente i dati<br>");
		
		String finalResult = "";
		
		try {
			int result = DatabaseManagementSingleton.getIstance()
													.insertIntoDB(userName, textMessage, ldt);
			if(result != 0) {
				finalResult += "Inserimento dati nel DB completato<br>";
				finalResult += DatabaseManagementSingleton.getIstance()
														  .retriveFromServer();
			}
			else {
				finalResult += "inserimento dati nel DB fallito<br>";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
