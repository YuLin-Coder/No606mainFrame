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

import javaBean.SupplierBean;
import page.Pageable;
import sql.DataProcess;

/**
 * Servlet implementation class SelectSupplier
 */
@WebServlet("/SelectSupplier")
public class SelectSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectSupplier() {
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
		String supplierid=request.getParameter("supplierid");
		String suppliername=request.getParameter("suppliername");
		String cp=request.getParameter("cp");
		int	a=Integer.parseInt(cp);
		DataProcess dp=new DataProcess();
		String sql="select count(*) from supplier where 1=1 ";
		String sqlx="";
		if(supplierid!=null&&!"".equals(supplierid.trim())) {
			sqlx+="and supplier_id='"+supplierid+"' ";
		}
		if(suppliername!=null&&!"".equals(suppliername.trim())) {
			sqlx+="and supplier_name='"+suppliername+"' ";
		}
		sql+=sqlx;
		try {
			int count=dp.Count(sql);
			Pageable page=new Pageable(count);
			page.setPageSize(8);
			page.setCurrentPage(a);
			sql="select* from supplier where supplier_id >=(select supplier_id from supplier order by supplier_id limit "+(page.getRowsCount()-1)+",1)";
			sql+=sqlx;
			sql+="order by supplier_id limit 0,"+page.getCurrentPageRowsCount();
			ResultSet rs=dp.getResult(sql);
			List<SupplierBean> supplierlist= new ArrayList<SupplierBean>();
            SupplierBean supplier=null;
            while(rs.next()){
                supplier=new SupplierBean();
                supplier.setSupplierId(rs.getString("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setSupplierTel(rs.getString("supplier_tel"));
                supplier.setSupplierAdd(rs.getString("supplier_add"));
                supplierlist.add(supplier);
            }
            rs.close();
            dp.closeConn();
            request.setAttribute("supplierlist",supplierlist);
            request.setAttribute("page",page);
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/supplier_list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
