package insert;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class InsertSupplier
 */
@WebServlet("/InsertSupplier")
public class InsertSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSupplier() {
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
		String supplierid=request.getParameter("supplierid");
		String suppliername=request.getParameter("suppliername");
		String suppliertel=request.getParameter("suppliertel");
		String supplieradd=request.getParameter("supplieradd");
		if(supplierid.trim()==""||suppliername.trim()==""||suppliertel.trim()==""||supplieradd.trim()=="") {
			request.setAttribute("msg","插入失败");
			request.getRequestDispatcher("/supplier_edit.jsp").forward(request, response);
		}else {
			String sql="insert into supplier values('"+supplierid+"','"+suppliername+"','"+suppliertel+"','"+supplieradd+"')";
			int i=con.ExeQuery(sql);
			if(i==-1) {
				request.setAttribute("msg","插入失败");
			}else {
				request.setAttribute("msg","插入成功");
			}
			request.getRequestDispatcher("/supplier_edit.jsp").forward(request, response);
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
