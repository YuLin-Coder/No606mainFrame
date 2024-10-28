package check;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sql.DataProcess;

/**
 * Servlet implementation class PasswordChange
 */
@WebServlet("/PasswordChange")
public class PasswordChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordChange() {
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
		PrintWriter out=response.getWriter();
		HttpSession session  = request.getSession(); 
		DataProcess con=new DataProcess();
		String username=(String)session.getAttribute("username");
		String password=request.getParameter("newPswd");
        String sql="update localauth set passwd='"+password+"' where user='"+username+"'";
        int i=con.ExeQuery(sql);
        if(i==1) {
        	out.print("<script language='javascript'>alert('修改成功');window.location.href='introduce.html';</script>");
        }else {
        	out.print("<script language='javascript'>alert('修改失败');window.location.href='introduce.html';</script>");
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
