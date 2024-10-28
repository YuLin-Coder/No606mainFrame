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

import sql.DataProcess;
import javaBean.CarPurchaseBean;
import page.Pageable;
/**
 * Servlet implementation class SelectCarPurchase
 */
@WebServlet("/SelectCarPurchase")
public class SelectCarPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCarPurchase() {
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
		String id=request.getParameter("id");
		String staffid=request.getParameter("staffid");
		String supplierid=request.getParameter("supplierid");
		String cp=request.getParameter("cp");
		DataProcess dp=new DataProcess();
		int	a=Integer.parseInt(cp);
		String sql,sqlx;
		sql="select count(*) from car_purchase natural join car where 1=1 ";
		sqlx="";
		if(id!=null&&!"".equals(id.trim())) {
			sqlx+="and certificate_id='"+id+"' ";
		}
		if(staffid!=null&&!"".equals(staffid.trim())) {
			sqlx+="and staff_id_card='"+staffid+"' ";
		}
		if(supplierid!=null&&!"".equals(supplierid.trim())) {
			sqlx+="and supplier_id='"+supplierid+"' ";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from car_purchase natural join car "
					+ "where certificate_id>="
					+ "(select certificate_id from car_purchase order by certificate_id limit "+(page.getRowsCount()-1)+",1) ";
			sql+=sqlx;
			sql+="order by certificate_id limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<CarPurchaseBean> carpurchaselist= new ArrayList<CarPurchaseBean>();
			CarPurchaseBean carpurchase=null;
	        while(rs.next()){
	        	carpurchase=new CarPurchaseBean();
	            carpurchase.setCertificateId(rs.getString("certificate_id"));
	            carpurchase.setLicenseId(rs.getString("license_id"));
	            carpurchase.setBrand(rs.getString("brand"));
	           	carpurchase.setCarType(rs.getString("car_type"));
	           	carpurchase.setColor(rs.getString("color"));
	           	carpurchase.setStaffId(rs.getString("staff_id_card"));
	           	carpurchase.setSupplierId(rs.getString("supplier_id"));
	           	carpurchase.setPurTime(rs.getString("purchase_time"));
	           	carpurchase.setTaxCost(rs.getString("purchase_price"));
	           	carpurchase.setCarState(rs.getString("car_state"));
	           	carpurchaselist.add(carpurchase);
	        }
	        rs.close();
            dp.closeConn();
	        request.setAttribute("carpurchaselist",carpurchaselist);
	        request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher("/car_purchase_list.jsp").forward(request, response);
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
