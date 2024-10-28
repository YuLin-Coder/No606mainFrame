package list;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class ListIncome
 */
@WebServlet("/ListIncome")
public class ListIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListIncome() {
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
		String sql;
		String p;
		try {
			Statement stm=c.createStatement();
			ResultSet rs;
			for(int i=0;i<7;i++) {
				sql="select COALESCE(SUM(pay_price),0) as p "+
						"from (select* from car_sales "+
								"where PERIOD_DIFF(date_format(now(),'%Y%m'),"+
								"date_format(sales_time,'%Y%m'))="+i+" and sales='销售') as sales"
										+ " group by date_format(sales_time,'%Y-%m')";
				rs=stm.executeQuery(sql);
				if(rs.next()) {
					p=rs.getString("p");
				}else {
					p="0";
				}
				request.setAttribute("C"+i,p);
				sql="select sum(parts_num*service_price) as p "+
						"from (select* from car_parts natural join parts "+
								"where PERIOD_DIFF(date_format(now(),'%Y%m'),"+
								"date_format(service_time,'%Y%m'))="+i+") as sales"
										+ " group by date_format(service_time,'%Y-%m')";
				rs=stm.executeQuery(sql);
				if(rs.next()) {
					p=rs.getString("p");
				}else {
					p="0";
				}
				request.setAttribute("P"+i,p);
			}
			sql="select sum(pay_price) as sumC from car_sales";
			rs=stm.executeQuery(sql);	
			if(rs.next()) {
				p=rs.getString("sumC");
			}else {
				p="0";
			}
			request.setAttribute("sumC",p);
			sql="select sum(p) as sumP from (select parts_num*service_price as p from car_parts natural join parts ) as sales";
			rs=stm.executeQuery(sql);	
			if(rs.next()) {
				p=rs.getString("sumP");
			}else {
				p="0";
			}
			request.setAttribute("sumP",p);
			stm.close();
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			request.getRequestDispatcher("income.jsp").forward(request, response);
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
