package kr.co.ezenac;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogInOk
 */
@WebServlet("/LogInOk")
public class LogInOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	private Statement stmt;
	private ResultSet resultSet;
	
	private String name,id,pw,phone1,phone2,phone3,gender;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		actionDo(request,response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		id=request.getParameter("id");
		pw=request.getParameter("pw");
		
		String query="select * from memberpe where id='"+id+"' and pw='"+pw+"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
			stmt=connection.createStatement();
			resultSet=stmt.executeQuery(query);
			
			while(resultSet.next()) {
				name=resultSet.getString("name");
				id=resultSet.getString("id");
				pw=resultSet.getString("pw");
				phone1=resultSet.getString("phone1");
				phone2=resultSet.getString("phone2");
				phone3=resultSet.getString("phone3");
				gender=resultSet.getString("gender");
			}
			HttpSession session=request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("id", id);
			session.setAttribute("pw",pw);
			
			response.sendRedirect("loginResult.jsp");
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt != null)
					stmt.close();
				if(connection != null)
					connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}










