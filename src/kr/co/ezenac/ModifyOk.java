package kr.co.ezenac;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ModifyOk
 */
@WebServlet("/ModifyOk")
public class ModifyOk extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection connection;	// 1번
    private Statement stmt;			// 2번
    
    private String name,id,pw,phone1,phone2,phone3,gender; // 6번
    HttpSession session; // 7번
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyOk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		actionDo(request,response); // 3번
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		actionDo(request,response); // 4번
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		session=request.getSession();
		
		name= request.getParameter("name");
		id=(String)session.getAttribute("id");
		pw= request.getParameter("pw");
		phone1= request.getParameter("phone1");
		phone2= request.getParameter("phone2");
		phone3= request.getParameter("phone3");
		gender= request.getParameter("gender");
		
	 // 5번 actionDo 메소드 만들기
	
		if(pwConfirm()) {
			System.out.println("OK");
			
			String query="update memberpe set name='"+name+"',phone1='"+phone1+"',phone2='"+phone2+"',phone3='"+phone3+"',gender='"+gender+"' where id='"+id+"'";
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
				stmt=connection.createStatement();
				int i=stmt.executeUpdate(query);
				if(i == 1) {
					System.out.println("update sucess");
					session.setAttribute("name",name);
					response.sendRedirect("modifyResult.jsp");
				}else {
					System.out.println("update fail");
					response.sendRedirect("modify.jsp");
				}
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
		}else {
			System.out.println("NG");
		}
}
	
		private boolean pwConfirm() {
			boolean rs=false;
			String sessionPw=(String)session.getAttribute("pw");
			
			if(sessionPw.equals(pw)) {
				rs=true;
			}else {
				rs=false;
			}
			
			return rs;
		}
	
}

	