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

import javaBean.CarPartsBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectCarParts
 */
@WebServlet("/SelectCarParts")
public class SelectCarParts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCarParts() {
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
		String service=request.getParameter("service");
		String staffid=request.getParameter("staffid");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from car_parts where 1=1 ";
		String sqlx="";
		if(certificateid!=null&&!"".equals(certificateid.trim())) {
			sqlx+="and certificate_id='"+certificateid+"' ";
		}
		if(staffid!=null&&!"".equals(staffid.trim())) {
			sqlx+="and staff_id='"+staffid+"' ";
		}
		if(service!="") {
			sqlx+="and service='"+service+"' ";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			System.out.println(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from car_parts natural join parts where 1=1 ";
			sql+=sqlx;
			sql+="order by certificate_id limit "+(page.getRowsCount()-1)+","+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<CarPartsBean> carpartslist= new ArrayList<CarPartsBean>();
			CarPartsBean carparts=null;
            while(rs.next()){
            	carparts=new CarPartsBean();
            	carparts.setCertificateId(rs.getString("certificate_id"));
            	carparts.setPartsName(rs.getString("parts_name"));
            	carparts.setStaffId(rs.getString("staff_id"));
            	carparts.setService(rs.getString("service"));
            	carparts.setServiceTime(rs.getString("service_time"));
            	carparts.setServicePrice(rs.getString("service_price"));
            	carparts.setPartsNum(rs.getString("parts_num"));
            	carpartslist.add(carparts);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("carpartslist",carpartslist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/car_parts_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
