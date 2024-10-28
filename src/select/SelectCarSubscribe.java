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

import javaBean.CarSubscribeBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectCarSubscribe
 */
@WebServlet("/SelectCarSubscribe")
public class SelectCarSubscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCarSubscribe() {
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
		String certificateid=request.getParameter("certificateid");
		String customerid=request.getParameter("customerid");
		String staffid=request.getParameter("staffid");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from car_sales where sales='预订' ";
		String sqlx="";
		if(certificateid!=null&&!"".equals(certificateid.trim())) {
			sqlx+="and certificate_id='"+certificateid+"' ";
		}
		if(customerid!=null&&!"".equals(customerid.trim())) {
			sqlx+="and customer_id='"+customerid+"' ";
		}
		if(staffid!=null&&!"".equals(staffid.trim())) {
			sqlx+="and staff_id='"+staffid+"' ";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			System.out.println(count);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			System.out.println(page.getRowsCount()-1);
			sql="select* from car_sales where sales='预订' and"
					+ " certificate_id>=(select certificate_id from car_sales where sales='预订' order by certificate_id limit "+(page.getRowsCount()-1)+",1) ";
			sql+=sqlx;
			sql+="order by certificate_id limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<CarSubscribeBean> carsublist= new ArrayList<CarSubscribeBean>();
			CarSubscribeBean carsub=null;
            while(rs.next()){
            	carsub=new CarSubscribeBean();
            	carsub.setCertificateId(rs.getString("certificate_id"));
            	carsub.setCustomerId(rs.getString("customer_id"));
            	carsub.setDeadLine(rs.getString("dead_line"));
            	carsub.setPayPrice(rs.getString("pay_price"));
            	carsub.setStaffId(rs.getString("staff_id"));
            	carsub.setSubTime(rs.getString("sales_time"));
            	carsublist.add(carsub);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("carsublist",carsublist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/car_subscribe_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
