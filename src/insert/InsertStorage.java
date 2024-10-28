package insert;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class InsertStorage
 */
@WebServlet("/InsertStorage")
public class InsertStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStorage() {
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
		String storageid=request.getParameter("storageid");
		String storagename=request.getParameter("storagename");
		String storagecap=request.getParameter("storagecap");
		String staffid=request.getParameter("staffid");
		String sql="select id_card from staff where staff_category='仓库管理' and id_card='"+staffid+"'";
		if(storageid.trim()==""||storagename.trim()==""||storagecap.trim()==""||staffid.trim()=="") {
			request.setAttribute("msg","插入失败");
			request.getRequestDispatcher("/storage_edit.jsp").forward(request, response);
		}else if(con.isExist(sql)==0) {
			request.setAttribute("msg","该员工不是仓库管理员");
			request.getRequestDispatcher("/storage_edit.jsp").forward(request, response);
		}
		else {
			sql="insert into storage values('"+storageid+"','"+storagename+"','"+storagecap+"','"+staffid+"')";
			int i=con.ExeQuery(sql);
			if(i==-1) {
				request.setAttribute("msg","插入失败");
			}else {
				request.setAttribute("msg","插入成功");
			}
			request.getRequestDispatcher("/storage_edit.jsp").forward(request, response);
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
