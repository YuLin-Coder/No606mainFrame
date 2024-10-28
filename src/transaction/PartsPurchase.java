package transaction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.DataProcess;

/**
 * Servlet implementation class PartsPurchase
 */
@WebServlet("/PartsPurchase")
public class PartsPurchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartsPurchase() {
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
		String partspurid=request.getParameter("partspurid");
		String partsid=request.getParameter("partsid");
		String partsname=request.getParameter("partsname");
		String partsprice=request.getParameter("partsprice");
		String purtime=request.getParameter("purtime");
		String puramount=request.getParameter("puramount");
		String staffid=request.getParameter("staffid");
		String supplierid=request.getParameter("supplierid");
		String storageid=request.getParameter("storageid");
		String sql="select storage_capacity from storage where storage_id='"+storageid+"'";
		int cap=con.Count(sql);
		sql="select id_card from staff where staff_category='采购' and id_card='"+staffid+"'";
		try {
			Double cny = Double.parseDouble(partsprice);
			DecimalFormat df = new DecimalFormat("0.00");   
			partsprice = df.format(cny);  
		    int amount = Integer.parseInt(puramount);
		    if(cap<amount*5) {
		    	request.setAttribute("msg","库存容量不足");
				request.getRequestDispatcher("/parts_purchase_edit.jsp").forward(request, response);
		    }else if(con.isExist(sql)==0) {
		    	request.setAttribute("msg","该员工不是采购人员");
				request.getRequestDispatcher("/parts_purchase_edit.jsp").forward(request, response);
		    }else {
		    	sql="select* from parts where parts_id='"+partsid+"'";
		    	try{
		    		Statement stm=c.createStatement();
		    		ResultSet rs=stm.executeQuery(sql);
		    		c.setAutoCommit(false);
		    		if(rs.next()) {
		    			if(!rs.getString("parts_name").equals(partsname)||!(rs.getString("parts_price").equals(partsprice))
		    					||!rs.getString("supplier_id").equals(supplierid)) {
		    				request.setAttribute("msg","输入配件信息有误");
		    				request.getRequestDispatcher("/parts_purchase_edit.jsp").forward(request, response);
		    				return;
		    			}
		    		}else {
		    			sql="insert into parts values('"+partsid+"','"+partsname+"','"+partsprice+"','"+supplierid+"')";
		    			stm.executeUpdate(sql);
		    		}
				    cap=cap-amount*5;
				    sql="update storage set storage_capacity='"+cap+"' where storage_id='"+storageid+"'";
				    stm.executeUpdate(sql);
				    sql="select parts_id from parts_storage where parts_id='"+partsid+"' and storage_id='"+storageid+"'";
				    if(con.isExist(sql)==1) {
				   	 	sql="update parts_storage set parts_num=parts_num+"+amount+" where parts_id='"+partsid+"' and storage_id='"+storageid+"'";
				    }else {
				    	sql="insert into parts_storage values('"+partsid+"','"+storageid+"','"+amount+"')";
				    }
				    stm.executeUpdate(sql);
				    sql="insert into parts_purchase values('"+partspurid+"','"+partsid+"','"+staffid+"','"+purtime+"','"+amount+"')";
				    stm.executeUpdate(sql);
				    stm.close();
				    c.commit();//try的最后提交事务  
				    request.setAttribute("msg","提交成功");				    
					request.getRequestDispatcher("/parts_purchase_edit.jsp").forward(request, response);
					
		    	} catch(Exception e) {
					request.setAttribute("msg","提交失败");	
					e.printStackTrace();
				    try {
						c.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}//回滚事务
				    request.getRequestDispatcher("/parts_purchase_edit.jsp").forward(request, response);
		    	}
		    }
		} catch (NumberFormatException e) {
			request.setAttribute("msg","请输入正确的价格和数量");
			request.getRequestDispatcher("/parts_purchase_edit.jsp").forward(request, response);
		    e.printStackTrace();
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
