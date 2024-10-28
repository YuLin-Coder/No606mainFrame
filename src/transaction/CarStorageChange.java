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
 * Servlet implementation class UpdateCarStorage
 */
@WebServlet("/CarStorageChange")
public class CarStorageChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarStorageChange() {
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
		String storageid=request.getParameter("cstorageid");
		String certificateid=request.getParameter("certificateid");
		String changeid=request.getParameter("newStorage");
		String sql="select storage_capacity from storage where storage_id='"+changeid+"'";
		int newCap=con.Count(sql);
		sql="select storage_capacity from storage where storage_id='"+storageid+"'";
		int oldCap=con.Count(sql);
		if(newCap<=40) {
			request.setAttribute("msg","库存容量不足");
			request.getRequestDispatcher("/car_storage_list.jsp").forward(request, response);
		}else {
			try{
			     c.setAutoCommit(false);//开启事务
			     newCap=newCap-40;
			     oldCap=oldCap+40;
			     sql="update storage set storage_capacity='"+newCap+"' where storage_id='"+changeid+"'";
			     Statement stm=c.createStatement();
			     stm.executeUpdate(sql);
			     sql="update storage set storage_capacity='"+oldCap+"' where storage_id='"+storageid+"'";
			     stm.executeUpdate(sql);
			     sql="update car_storage set storage_id='"+changeid+"' where certificate_id='"+certificateid+"'";
			     stm.executeUpdate(sql);
			     stm.close();
			     c.commit();//try的最后提交事务  
			     request.setAttribute("msg","库存转移成功");
			} catch(Exception e) {
				request.setAttribute("msg","库存转移失败");	
				e.printStackTrace();
			    try {
					c.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//回滚事务
			}finally {
				request.getRequestDispatcher("/car_storage_list.jsp").forward(request, response);
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
