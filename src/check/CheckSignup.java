package check;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;


/**
 * Servlet implementation class CheckSignup
 */
@WebServlet("/CheckSignup")
public class CheckSignup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSignup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		DataProcess con=new DataProcess();
		String username=request.getParameter("username");
		String password=request.getParameter("pswd");
		request.getParameter("repswd");
		String sql="insert into localauth values('"+username+"','"+password+"')";
		int i=con.ExeQuery(sql);
		if(i==1) {
			request.setAttribute("demo","注册成功");
			request.getRequestDispatcher("/signin.jsp").forward(request, response);
		}else {
			request.setAttribute("demo","该用户已被注册");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	

}
