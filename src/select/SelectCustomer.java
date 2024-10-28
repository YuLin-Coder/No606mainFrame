package select;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaBean.CustomerBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectCustomer
 */
@WebServlet("/SelectCustomer")
public class SelectCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCustomer() {
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
		String customername=request.getParameter("customername");
		String customersex=request.getParameter("customersex");
		String customerid=request.getParameter("customerid");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from customer where 1=1 ";
		String sqlx="";
		if(customername!=null&&!"".equals(customername.trim())) {
			sqlx+="and customer_name='"+customername+"' ";
		}
		if(customerid!=null&&!"".equals(customerid.trim())) {
			sqlx+="and id_card='"+customerid+"' ";
		}
		if(customersex!="") {
			sqlx+="and customer_sex='"+customersex+"'";
		}		
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from customer where id_card >=(select id_card from customer order by id_card limit "+(page.getRowsCount()-1)+",1)";
			sql+=sqlx;
			sql+="order by id_card limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<CustomerBean> customerlist= new ArrayList<CustomerBean>();
            CustomerBean customer=null;
            while(rs.next()){
            	customer=new CustomerBean();
            	customer.setCustomerId(rs.getString("id_card"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerSex(rs.getString("customer_sex"));
                customer.setCustomerTel(rs.getString("customer_tel"));
                customer.setCustomerAdd(rs.getString("customer_add"));     
                customerlist.add(customer);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("customerlist",customerlist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
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
