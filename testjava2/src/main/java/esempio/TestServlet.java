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
@WebServlet("/TestServlet")
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
		
		String risposta = "Lista messaggi: ";
		try {
			risposta += this.retriveFromServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.getWriter().append(risposta);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String retriveFromServer() throws Exception{

		LocalDateTime lastCall = LocalDateTime.MIN;
		String driver = "org.mariadb.jdbc.Driver";
		Class.forName(driver);
		
		String url = "jdbc:mariadb://centauri.proximainformatica.com:193/academyfs07";
		
		Connection con = DriverManager.getConnection(url, "acfs07", "acfs07");
		
		Statement cmd = con.createStatement();
		String query = "SELECT * FROM messages WHERE '" + lastCall + "' < serverReceivedTime;";
		ResultSet res = cmd.executeQuery(query);

		String daRestituire ="";
		
		while (res.next()) {
			daRestituire += (res.getString(2));
			daRestituire += (res.getString(3));
			daRestituire+= (res.getString(4));
			daRestituire+= (res.getString(5)).concat("\n");
		}
		
		return daRestituire;
	}

}
