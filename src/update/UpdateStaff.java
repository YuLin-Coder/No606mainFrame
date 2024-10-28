package update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class UpdateStaff
 */
@WebServlet("/UpdateStaff")
public class UpdateStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStaff() {
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
		String preid=request.getParameter("preid");
		String staffname=request.getParameter("sname");
		String staffid=request.getParameter("sid");
		String sex=request.getParameter("ssex");
		String stafftel=request.getParameter("stel");
		String staffjob=request.getParameter("sjob");
		String staffaddress=request.getParameter("saddress");
		String sql="update staff set staff_name='"+staffname+"',"+
				"id_card='"+staffid+"',"+
				"staff_sex='"+sex+"',"+
				"staff_tel='"+stafftel+"',"+
				"staff_category='"+staffjob+"',"+
				"staff_address='"+staffaddress+"'"
				+ " where id_card='"+preid+"'";
		int i=con.ExeQuery(sql);
		if(i==-1) {
			request.setAttribute("msg","修改失败");	
		}else {
			request.setAttribute("msg","修改成功");
		}
		request.getRequestDispatcher("/staff_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
