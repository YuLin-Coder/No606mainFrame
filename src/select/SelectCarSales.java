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

import javaBean.CarSalesBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectCarSales
 */
@WebServlet("/SelectCarSales")
public class SelectCarSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCarSales() {
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
		String staffid=request.getParameter("staffid");
		String salestime=request.getParameter("salestime");
		String cp=request.getParameter("cp");
		DataProcess dp=new DataProcess();
		int	a=Integer.parseInt(cp);
		String sql,sqlx;
		sql="select count(*) from " + 
				"(select * from car_sales where sales='销售') as sale " + 
				"natural join car_purchase natural join car where 1=1 ";
		sqlx="";
		if(certificateid!=null&&!"".equals(certificateid.trim())) {
			sqlx+="and certificate_id='"+certificateid+"' ";
		}
		if(staffid!=null&&!"".equals(staffid.trim())) {
			sqlx+="and staff_id='"+staffid+"' ";
		}
		if(salestime!=null&&!"".equals(salestime.trim())) {
			if(salestime.equals("1")) {
				sqlx+="and sales_time>DATE_SUB(CURDATE(), INTERVAL 3 MONTH) ";
			}else if(salestime.equals("2")) {
				sqlx+="and sales_time>DATE_SUB(CURDATE(), INTERVAL 6 MONTH) ";
			}else {
				sqlx+="and sales_time>DATE_SUB(CURDATE(), INTERVAL 1 YEAR) ";
			}
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from (select * from car_sales where sales='销售') as sale "
					+ "natural join car_purchase natural join car where 1=1 ";
			sql+=sqlx;
			sql+="order by certificate_id limit "+(page.getRowsCount()-1)+","+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<CarSalesBean> carsaleslist= new ArrayList<CarSalesBean>();
			CarSalesBean carsales=null;
	        while(rs.next()){
	        	carsales=new CarSalesBean();
	            carsales.setCertificateId(rs.getString("certificate_id"));
	            carsales.setLicenseId(rs.getString("license_id"));
	            carsales.setBrand(rs.getString("brand"));
	           	carsales.setCarType(rs.getString("car_type"));
	           	carsales.setColor(rs.getString("color"));
	           	carsales.setStaffId(rs.getString("staff_id"));
	           	carsales.setSupplierId(rs.getString("supplier_id"));
	           	carsales.setSalesTime(rs.getString("sales_time"));
	           	carsales.setPayPrice(rs.getString("pay_price"));
	           	carsales.setPurchasePrice(rs.getString("purchase_price"));
	           	carsales.setCustomerId(rs.getString("customer_id"));
	           	carsaleslist.add(carsales);
	        }
	        rs.close();
            dp.closeConn();
	        request.setAttribute("carsaleslist",carsaleslist);
	        request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher("/car_sales_list.jsp").forward(request, response);
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
