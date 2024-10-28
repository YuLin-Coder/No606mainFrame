package transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class CarSales
 */
@WebServlet("/CarSales")
public class CarSales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarSales() {
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
		Connection c=con.getConn();
		String certificateid=request.getParameter("certificateid");
		String salestime=request.getParameter("salestime");
		String customerid=request.getParameter("customerid");
		String payprice=request.getParameter("payprice");
		String staffid=request.getParameter("staffid");
		try{
		     Statement stm=c.createStatement();
		     String sql="select id_card from customer where id_card='"+customerid+"'";
		     ResultSet rs=stm.executeQuery(sql);
		     if(rs.next()) {
		    	 sql="select* from car where certificate_id='"+certificateid+"'";
		    	 rs=stm.executeQuery(sql);
		    	 if(rs.next()) {
		    		 if(rs.getString("car_state").equals("预订")||rs.getString("car_state").equals("已售")) {
		    			 request.setAttribute("msg","该车已被预订或售出");
		    		 }else {
		    			 sql="select id_card from staff where staff_category='销售' and id_card='"+staffid+"'";
		    			 rs=stm.executeQuery(sql);
		    			 if(!rs.next()) {
		    				 request.setAttribute("msg","该员工不是销售");					    
		    			 }
		    			 else {
			    			 c.setAutoCommit(false);//开启事务
			    			 sql="update car set car_state='已售' where certificate_id='"+certificateid+"'";
			    			 stm.executeUpdate(sql);
			    			 sql="insert into car_sales values('"+certificateid+"','"+customerid+"','"+staffid+"','销售','"+payprice+"','"+salestime+"',null)";
			    			 stm.executeUpdate(sql);
			    			 sql="update storage set storage_capacity=storage_capacity+40 "
			    						+ "where storage_id=(select storage_id "
			    						+ "from car_storage where certificate_id='"+certificateid+"')";
			    			 stm.executeUpdate(sql);
			    			 sql="delete from car_storage where certificate_id='"+certificateid+"'";
			    			 stm.executeUpdate(sql);			    
						     c.commit();//try的最后提交事务  
			    			 request.setAttribute("msg","订单提交成功");
		    			 }
		    			 stm.close();
		    			 rs.close();
		    		 }
		    	 }else {
		    		 request.setAttribute("msg","库存中不存在该车辆");
		    	 }
		     }else {
		    	 request.setAttribute("msg","暂无该客户信息");
		     }
		     request.getRequestDispatcher("/car_sales_edit.jsp").forward(request, response);		     
		} catch(Exception e) {
			request.setAttribute("msg","提交销售单失败");	
			e.printStackTrace();
		    try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				request.getRequestDispatcher("/car_sales_edit.jsp").forward(request, response);
			}
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
