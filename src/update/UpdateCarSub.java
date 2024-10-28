package update;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class UpdateCarSub
 */
@WebServlet("/UpdateCarSub")
public class UpdateCarSub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCarSub() {
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
		String certificateid=request.getParameter("cid");
		Date date=new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
		String time=df.format(date);
		Connection c=con.getConn();
		try {
			String sql="update car set car_state='已售' where certificate_id='"+certificateid+"'";
			Statement stm=c.createStatement();
			c.setAutoCommit(false);//开启事务
			stm.executeUpdate(sql);
			sql="update car_sales set sales='销售',sales_time='"+time+"' where certificate_id='"+certificateid+"'";
			stm.executeUpdate(sql);
			sql="update storage set storage_capacity=storage_capacity+40 "
					+ "where storage_id=(select storage_id "
					+ "from car_storage where certificate_id='"+certificateid+"')";
			stm.executeUpdate(sql);
			sql="delete from car_storage where certificate_id='"+certificateid+"'";
			stm.executeUpdate(sql);
		    stm.close();
		    c.commit();//try的最后提交事务  
		    request.setAttribute("msg","结算成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			request.setAttribute("msg","结算失败");	
			e.printStackTrace();
		    try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher("/car_subscribe_list.jsp").forward(request, response);
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
