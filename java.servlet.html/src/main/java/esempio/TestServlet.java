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
		// TODO Auto-generated method stub
//		response.getWriter().append("Daim Served at: ").append(request.getContextPath());
		
		//Verifico input ricevuto da front end
		String userName = request.getParameter("userName"); //deve coincidere con il nome dei parametri in html
		String textMessage = request.getParameter("textMessage");
		String ldt = request.getParameter("datetime");
		
		System.out.println("userName: " + userName);
		System.out.println("textMessage: " + textMessage);
		
		response.getWriter().append("Ho ricevuto correttamente i dati\n");
		
		try {
			int result = insertIntoDB(userName, textMessage, ldt);
			if(result != 0) {
				response.getWriter().append("Inserimento dati nel DB completato\n");
			}
			else {
				response.getWriter().append("inserimento dati nel DB fallito\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private int insertIntoDB(String username, String textMessage, String ldt) throws Exception{

		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "INSERT INTO messages(userName, textMessage, userInsertedTime ,serverReceivedTime) values"
				+ "('" + username + "'," 
				+ "'" + textMessage + "',"
				+ "'" + ldt + "'," 
				+ "'" + ldt + "');";
		
		int rowsUpdated = cmd.executeUpdate(query);
		
		return rowsUpdated;
	}

}
