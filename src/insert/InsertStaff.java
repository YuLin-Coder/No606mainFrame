package insert;
import sql.DataProcess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InsertStaff
 */
@WebServlet("/InsertStaff")
public class InsertStaff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertStaff() {
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
		String staffname=request.getParameter("staffname");
		String staffid=request.getParameter("staffid");
		String sex=request.getParameter("staffsex");
		String stafftel=request.getParameter("stafftel");
		String staffjob=request.getParameter("staffjob");
		String staffaddress=request.getParameter("staffaddress");
		if(staffname.trim()==""||staffid.trim()==""||sex.trim()==""||stafftel.trim()==""||staffjob.trim()==""||staffaddress.trim()=="") {
			request.setAttribute("msg","插入失败");
			request.getRequestDispatcher("/staff_edit.jsp").forward(request, response);	
		}else {
			String sql="insert into staff values('"+staffname+"','"+staffid+"','"+sex+"','"+staffjob+"','"+stafftel+"','"+staffaddress+"')";
			int i=con.ExeQuery(sql);
			if(i==-1) {
				request.setAttribute("msg","插入失败");
			}else {
				request.setAttribute("msg","插入成功");
			}
			request.getRequestDispatcher("/staff_edit.jsp").forward(request, response);
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
