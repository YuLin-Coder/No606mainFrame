package select;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;

import sql.DataProcess;
import javaBean.StaffBean;
import page.Pageable;
/**
 * Servlet implementation class SelectStaff
 */
@WebServlet("/SelectStaff")
public class SelectStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStaff() {
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
		String staffname=request.getParameter("staffname");
		String staffsex=request.getParameter("staffsex");
		String staffid=request.getParameter("staffid");
		String staffjob=request.getParameter("staffjob");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from staff where 1=1 ";
		String sqlx="";
		if(staffname!=null&&!"".equals(staffname.trim())) {
			sqlx+="and staff_name='"+staffname+"' ";
		}
		if(staffid!=null&&!"".equals(staffid.trim())) {
			sqlx+="and id_card='"+staffid+"' ";
		}
		if(staffsex!="") {
			sqlx+="and staff_sex='"+staffsex+"' ";
		}
		if(staffjob!=null&&!"".equals(staffjob.trim())) {
			sqlx+="and staff_category='"+staffjob+"' ";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			System.out.println(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from staff where id_card >=(select id_card from staff order by id_card limit "+(page.getRowsCount()-1)+",1) ";
			sql+=sqlx;
			sql+="order by id_card limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<StaffBean> stafflist= new ArrayList<StaffBean>();
            StaffBean staff=null;
            while(rs.next()){
                staff=new StaffBean();
                staff.setStaffId(rs.getString("id_card"));
                staff.setStaffJob(rs.getString("staff_category"));
                staff.setStaffName(rs.getString("staff_name"));
                staff.setStaffSex(rs.getString("staff_sex"));
                staff.setStaffTel(rs.getString("staff_tel"));
                staff.setStaffAdd(rs.getString("staff_address"));
                stafflist.add(staff);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("stafflist",stafflist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/staff_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
