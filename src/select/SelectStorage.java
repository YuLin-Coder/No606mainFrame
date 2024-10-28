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
import javaBean.StorageBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectStorage
 */
@WebServlet("/SelectStorage")
public class SelectStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStorage() {
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
		String storageid=request.getParameter("storageid");
		String staffid=request.getParameter("staffid");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from storage where 1=1 ";
		String sqlx="";
		if(storageid!=null&&!"".equals(storageid.trim())) {
			sqlx+="and storage_id='"+storageid+"' ";
		}
		if(staffid!=null&!"".equals(staffid.trim())) {
			sqlx+="and staff_id='"+staffid+"' ";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from storage left outer join staff on storage.staff_id=staff.id_card where storage_id >=(select storage_id from storage order by storage_id limit "+(page.getRowsCount()-1)+",1) ";
			sql+=sqlx;
			sql+="order by storage_id limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<StorageBean> storagelist= new ArrayList<StorageBean>();
            StorageBean storage=null;
            while(rs.next()){
            	storage=new StorageBean();
            	storage.setStaffId(rs.getString("id_card"));
            	storage.setStorageId(rs.getString("storage_id"));
            	storage.setStaffName(rs.getString("staff_name"));
            	storage.setStaffSex(rs.getString("staff_sex"));
            	storage.setStorageName(rs.getString("storage_name"));
            	storage.setStorageCapacity(rs.getString("storage_capacity"));
            	storagelist.add(storage);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("storagelist",storagelist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/storage_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
