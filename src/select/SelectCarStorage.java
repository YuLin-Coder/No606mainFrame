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

import javaBean.CarStorageBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectCarStorage
 */
@WebServlet("/SelectCarStorage")
public class SelectCarStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCarStorage() {
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
		String cid=request.getParameter("cid");
		String brand=request.getParameter("brand");
		String color=request.getParameter("color");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from car_storage where 1=1 ";
		String sqlx="";
		if(storageid!=null&&!"".equals(storageid.trim())) {
			sqlx+="and storage_id='"+storageid+"' ";
		}
		if(cid!=null&&!"".equals(cid.trim())) {
			sqlx+="and certificate_id='"+cid+"' ";
		}
		if(brand!=null&&!"".equals(brand.trim())) {
			sqlx+="and brand='"+brand+"' ";
		}
		if(color!=null&&!"".equals(color.trim())) {
			sqlx+="and color='"+color+"'";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select storage_id,brand,car_type,color,storage_capacity,certificate_id,car_state"
					+ " from car_storage natural join car natural join storage where "
					+ "certificate_id>=(select certificate_id from car_storage order by certificate_id limit "+(page.getRowsCount()-1)+",1) ";
			sql+=sqlx;
			sql+="order by certificate_id limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<CarStorageBean> carstoragelist= new ArrayList<CarStorageBean>();
			CarStorageBean carstorage=null;
            while(rs.next()){
            	carstorage=new CarStorageBean();
            	carstorage.setCarState(rs.getString("car_state"));
            	carstorage.setStorageId(rs.getString("storage_id"));
            	carstorage.setBrand(rs.getString("brand"));
            	carstorage.setCarType(rs.getString("car_type"));
            	carstorage.setColor(rs.getString("color"));
            	carstorage.setStorageCap(rs.getString("storage_capacity"));
            	carstorage.setCertificateId(rs.getString("certificate_id"));
                carstoragelist.add(carstorage);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("carstoragelist",carstoragelist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/car_storage_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
