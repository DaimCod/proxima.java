package esempio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteID")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //deve coincidere con il nome dei parametri in html
		
		response.getWriter().append("<html><body><h2>Scegliere l'id tra queste opzioni</h2><br>");
				
		
		String finalResult = "";
		
		try {
			response.getWriter().append(DatabaseManagementSingleton.getIstance()
			   						   .retriveFromDB());
			response.getWriter().append( 
					 "		<form>"
					+ "			  <label>ID da cancellare: </label>"
					+ "			  <input type=\"text\" name=\"id\"><br><br>"
					+ "			  <input type=\"submit\" value=\"Cancella ora\">"
					+ "		</form>");
			String id = request.getParameter("id");
			DatabaseManagementSingleton.getIstance()
									   .deleteFromDB(id);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//response.getWriter().append("<a href=\"./List\"> Clicca qui per visualizzare il cambiamento</a>");
		
		 
		
		response.getWriter().append("<a href=\"./List\"> Visualizza aggiornamento </a>");
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
