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
 * Servlet implementation class DeleteCarSub
 */
@WebServlet("/DeleteCarSub")
public class DeleteCarSub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCarSub() {
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
		String cid=request.getParameter("cid");
		try{
		     c.setAutoCommit(false);//开启事务
		     String sql="update car set car_state='在库' where certificate_id='"+cid+"'";
		     Statement stm=c.createStatement();
		     stm.executeUpdate(sql);
		     sql="delete from car_sales where certificate_id='"+cid+"'";
		     stm.executeUpdate(sql);
		     stm.close();
		     c.commit();//try的最后提交事务  
		     request.setAttribute("msg","删除成功");
		} catch(Exception e) {
			request.setAttribute("msg","删除失败");	
			e.printStackTrace();
		    try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//回滚事务
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
