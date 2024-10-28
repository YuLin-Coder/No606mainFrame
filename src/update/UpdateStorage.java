package update;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class UpdateStorage
 */
@WebServlet("/UpdateStorage")
public class UpdateStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStorage() {
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
		String storageid=request.getParameter("sid");
		String storagename=request.getParameter("storagename");
		String storagecap=request.getParameter("storagecap");
		String staffid=request.getParameter("stid");
		String sql="select id_card from staff where id_card='"+staffid+"' and staff_category='仓库管理'";
		if(con.isExist(sql)==1) {
			sql="update storage set storage_id='"+storageid+"',"+
				"storage_name='"+storagename+"',"+
				"storage_capacity='"+storagecap+"',"+
				"staff_id='"+staffid+"'"
				+ " where storage_id='"+preid+"'";
			int i=con.ExeQuery(sql);
			if(i==-1) {
				request.setAttribute("msg","修改失败");	
			}else {
				request.setAttribute("msg","修改成功");
			}
		}else {
			request.setAttribute("msg","该员工不是仓库管理员");
		}
		request.getRequestDispatcher("/storage_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
