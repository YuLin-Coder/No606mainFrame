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

import javaBean.PartPurchaseBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectPartPurchase
 */
@WebServlet("/SelectPartPurchase")
public class SelectPartPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectPartPurchase() {
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
		String partname=request.getParameter("partname");
		String staffid=request.getParameter("staffid");
		String supplierid=request.getParameter("supplierid");
		String cp=request.getParameter("cp");
		DataProcess dp=new DataProcess();
		int	a=Integer.parseInt(cp);
		String sql,sqlx;
		sql="select count(*) from parts_purchase natural join parts where 1=1 ";
		sqlx="";
		if(partname!=null&&!"".equals(partname.trim())) {
			sqlx+="and parts_name='"+partname+"' ";
		}
		if(staffid!=null&&!"".equals(staffid.trim())) {
			sqlx+="and staff_id_card='"+staffid+"' ";
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
			sql="select* from parts_purchase natural join parts where parts_purchase_id >=(select parts_purchase_id from parts_purchase order by parts_purchase_id limit "+(page.getRowsCount()-1)+",1) ";
			sql+=sqlx;
			sql+="order by parts_purchase_id limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<PartPurchaseBean> partpurchaselist= new ArrayList<PartPurchaseBean>();
			PartPurchaseBean partpurchase=null;
	        while(rs.next()){
	        	partpurchase=new PartPurchaseBean();
	        	partpurchase.setPartPurchaseId(rs.getString("parts_purchase_id"));
	        	partpurchase.setStaffId(rs.getString("staff_id_card"));
	        	partpurchase.setSupplierId(rs.getString("supplier_id"));
	        	partpurchase.setPurTime(rs.getString("purchase_time"));
	        	partpurchase.setPartPrice(rs.getString("parts_price"));
	        	partpurchase.setAmount(rs.getString("purchase_amount"));
	        	partpurchase.setPartName(rs.getString("parts_name"));
	        	partpurchaselist.add(partpurchase);
	        }
	        rs.close();
            dp.closeConn();
	        request.setAttribute("partpurchaselist",partpurchaselist);
	        request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/parts_purchase_list.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
