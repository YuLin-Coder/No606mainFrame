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

import javaBean.PartStorageBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectPartStorage
 */
@WebServlet("/SelectPartStorage")
public class SelectPartStorage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectPartStorage() {
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
		String partname=request.getParameter("partname");
		String supplierid=request.getParameter("supplierid");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*)"
				+ " from parts_storage natural join supplier natural join parts where 1=1 ";
		String sqlx="";
		if(storageid!=null&&!"".equals(storageid.trim())) {
			sqlx+="and storage_id='"+storageid+"' ";
		}
		if(partname!=null&&!"".equals(partname.trim())) {
			sqlx+="and	parts_name='"+partname+"' ";
		}
		if(supplierid!=null&&!"".equals(supplierid.trim())) {
			sqlx+="and supplier_id='"+supplierid+"'";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select storage_id,parts_name,parts_price,parts_num,supplier_name,supplier_id"
					+ " from parts_storage natural join supplier natural join parts where 1=1 ";
			sql+=sqlx;
			sql+="order by storage_id limit "+(page.getRowsCount()-1)+","+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<PartStorageBean> partstoragelist= new ArrayList<PartStorageBean>();
			PartStorageBean partstorage=null;
            while(rs.next()){
            	partstorage=new PartStorageBean();
            	partstorage.setStorageId(rs.getString("storage_id"));
            	partstorage.setPartName(rs.getString("parts_name"));
            	partstorage.setPartPrice(rs.getString("parts_price"));
            	partstorage.setAmount(rs.getString("parts_num"));
            	partstorage.setSupplierId(rs.getString("supplier_id"));
            	partstorage.setSupplierName(rs.getString("supplier_name"));
            	partstoragelist.add(partstorage);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("partstoragelist",partstoragelist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/parts_storage_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
