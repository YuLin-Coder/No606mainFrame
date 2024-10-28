package transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class CarParts
 */
@WebServlet("/CarParts")
public class CarParts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarParts() {
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
		String partsid=request.getParameter("partsid");
		String storageid=request.getParameter("storageid");
		String servicetime=request.getParameter("time");
		String service=request.getParameter("service");
		String staffid=request.getParameter("staffid");
		String serviceprice=request.getParameter("service_price");
		String sql="select* from car where certificate_id='"+certificateid+"' and car_state='已售'";
		String check="select* from parts_storage where storage_id='"+storageid+"' and parts_id='"+partsid+"'";
		String checkstaff="select* from staff where id_card='"+staffid+"' and staff_category='维修'";
		String sqlx="select service_price from car_parts where certificate_id='"+certificateid+"' "
				+ "and staff_id='"+staffid+"' and service='"+service+"' and parts_id='"+partsid+"' and service_time='"+servicetime+"'";
		String m=""+con.Count(sqlx);
		if(con.isExist(check)==0) {
			request.setAttribute("msg","库存中无此配件");
	    	request.getRequestDispatcher("/car_parts_edit.jsp").forward(request, response);
		}else if(con.isExist(checkstaff)==0) {
			request.setAttribute("msg","该员工不是维修人员");
	    	request.getRequestDispatcher("/car_parts_edit.jsp").forward(request, response);
		}else if(!m.equals(serviceprice)&&!m.equals("0")){
			request.setAttribute("msg","价格有误");
	    	request.getRequestDispatcher("/car_parts_edit.jsp").forward(request, response);
		}else if(con.isExist(sql)==1) {
			try {
				sql="select parts_num from parts_storage where storage_id='"+storageid+"' and parts_id='"+partsid+"'";
				int parts_num=con.Count(sql);
				parts_num--;
				if(parts_num==0){
					sql="delete from parts_storage where storage_id='"+storageid+"' and parts_id='"+partsid+"'";
				}else {
					sql="update parts_storage set parts_num=parts_num-1 where storage_id='"+storageid+"' and parts_id='"+partsid+"'";
				}
				c.setAutoCommit(false);
				Statement stm=c.createStatement();
				stm.executeUpdate(sql);
				if(m.equals(serviceprice)) {
					sql="update car_parts set parts_num=parts_num+1 where certificate_id='"+certificateid+"' "
							+ "and staff_id='"+staffid+"' and service='"+service+"' and parts_id='"+partsid+"' and service_time='"+servicetime+"'";
				}else if(m.equals("0")) {
					sql="insert into car_parts values('"+certificateid+"','"+partsid+"',1,'"+staffid+"','"+servicetime+"','"+serviceprice+"','"+service+"')";
				}
				stm.executeUpdate(sql);
				stm.close();
			    c.commit();//try的最后提交事务  
    			request.setAttribute("msg","提交成功");
		    	request.getRequestDispatcher("/car_parts_edit.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					request.setAttribute("msg","提交失败");
			    	request.getRequestDispatcher("/car_parts_edit.jsp").forward(request, response);
				}
			}
		}
		else {
			request.setAttribute("msg","该车尚未售出");
	    	request.getRequestDispatcher("/car_parts_edit.jsp").forward(request, response);
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
