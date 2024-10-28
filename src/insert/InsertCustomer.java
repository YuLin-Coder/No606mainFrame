package insert;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/InsertCustomer")
public class InsertCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCustomer() {
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
		String customername=request.getParameter("customername");
		String customerid=request.getParameter("customerid");
		String customersex=request.getParameter("customersex");
		String customertel=request.getParameter("customertel");
		String customeraddress=request.getParameter("customeraddress");
		if(customername.trim()==""||customerid.trim()==""||customersex.trim()==""||customertel.trim()==""||customeraddress.trim()=="") {
			request.setAttribute("msg","插入失败");
			request.getRequestDispatcher("/customer_edit.jsp").forward(request, response);
		}
		else {
			String sql="insert into customer values('"+customerid+"','"+customername+"','"+customersex+"','"+customertel+"','"+customeraddress+"')";
			int i=con.ExeQuery(sql);
			if(i==-1) {
				request.setAttribute("msg","插入失败");
			}else {
				request.setAttribute("msg","插入成功");
			}
			request.getRequestDispatcher("/customer_edit.jsp").forward(request, response);
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
