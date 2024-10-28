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
 * Servlet implementation class CarSubscribe
 */
@WebServlet("/CarSubscribe")
public class CarSubscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarSubscribe() {
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
		String subtime=request.getParameter("subtime");
		String customerid=request.getParameter("customerid");
		String submoney=request.getParameter("submoney");
		String deadline=request.getParameter("deadline");
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
			    			 sql="update car set car_state='预订' where certificate_id='"+certificateid+"'";
			    			 stm.executeUpdate(sql);
			    			 sql="insert into car_sales values('"+certificateid+"','"+customerid+"','"+staffid+"','预订','"+submoney+"','"+subtime+"','"+deadline+"')";
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
		     request.getRequestDispatcher("/car_subscribe_edit.jsp").forward(request, response);		     
		} catch(Exception e) {
			request.setAttribute("msg","提交预订单失败");	
			e.printStackTrace();
		    try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				request.getRequestDispatcher("/car_subscribe_edit.jsp").forward(request, response);
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
