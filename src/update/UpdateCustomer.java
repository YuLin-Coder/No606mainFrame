package update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class UpdateCustomer
 */
@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomer() {
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
		String customername=request.getParameter("cname");
		String customerid=request.getParameter("cid");
		String customersex=request.getParameter("csex");
		String customertel=request.getParameter("ctel");
		String customeraddress=request.getParameter("caddress");
		String sql="update customer set customer_name='"+customername+"',"+
				"id_card='"+customerid+"',"+
				"customer_sex='"+customersex+"',"+
				"customer_tel='"+customertel+"',"+
				"customer_add='"+customeraddress+"'"
				+ " where id_card='"+preid+"'";
		int i=con.ExeQuery(sql);
		if(i==-1) {
			request.setAttribute("msg","修改失败");	
		}else {
			request.setAttribute("msg","修改成功");
		}
		request.getRequestDispatcher("/customer_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
