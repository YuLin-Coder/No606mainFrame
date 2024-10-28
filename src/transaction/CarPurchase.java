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
 * Servlet implementation class CarPurchase
 */
@WebServlet("/CarPurchase")
public class CarPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarPurchase() {
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
		String licenseid=request.getParameter("licenseid");
		String purchasetime=request.getParameter("purtime");
		String brand=request.getParameter("brand");
		String supplierid=request.getParameter("supplierid");
		String cartype=request.getParameter("cartype");
		String staffid=request.getParameter("staffid");
		String taxcost=request.getParameter("taxcost");
		String storageid=request.getParameter("storageid");
		String color=request.getParameter("color");
		String sql="select storage_capacity from storage where storage_id='"+storageid+"'";
		int cap=con.Count(sql);
		sql="select id_card from staff where staff_category='采购' and id_card='"+staffid+"'";
		int isExist=con.isExist(sql);
		if(cap<40) {
			request.setAttribute("msg","库存容量不足");
			request.getRequestDispatcher("/car_purchase_edit.jsp").forward(request, response);
		}else if(isExist==0) {
			request.setAttribute("msg","该员工不是采购人员");
			request.getRequestDispatcher("/car_purchase_edit.jsp").forward(request, response);
		}
		else {
			try{
			     c.setAutoCommit(false);//开启事务
			     cap=cap-40;
			     sql="update storage set storage_capacity='"+cap+"' where storage_id='"+storageid+"'";
			     Statement stm=c.createStatement();
			     stm.executeUpdate(sql);
			     sql="insert into car values('"+licenseid+"','"+certificateid+"','"+brand+"','"+cartype+"','"+color+"','在库')";
			     stm.executeUpdate(sql);
			     sql="insert into car_storage values('"+certificateid+"','"+storageid+"')";
			     stm.executeUpdate(sql);
			     sql="insert into car_purchase values('"+certificateid+"','"+staffid+"','"+supplierid+"','"+purchasetime+"','"+taxcost+"')";
			     stm.executeUpdate(sql);
			     stm.close();
			     c.commit();//try的最后提交事务  
			     request.setAttribute("msg","提交成功");
			} catch(Exception e) {
				request.setAttribute("msg","提交失败,请检查合格证号或供应商信息");	
				e.printStackTrace();
			    try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//回滚事务
			}finally {
				request.getRequestDispatcher("/car_purchase_edit.jsp").forward(request, response);
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
