package page;
import java.sql.*;
public class Pageable {

	private int pageSize;
	private int totalRows;
	private int totalPages;
	private int currentPage;
	private int rowsCount;
	
	public Pageable(ResultSet rs) {
		try {
			rs.last();
			this.setTotalRows(rs.getRow());
			rs.beforeFirst();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public Pageable(int count) {
		this.setTotalRows(count);
	}
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize>=0) {
			this.pageSize=pageSize;
		}else {
			this.pageSize=1;
		}
		this.setTotalPages();
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages() {
		if(totalRows==0) {
			totalPages=0;
		}else if(pageSize==0) {
			totalPages=1;
		}else {
			if(totalRows%pageSize!=0) 
				totalPages=totalRows/pageSize+1;
			else
				totalPages=totalRows/pageSize;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int page) {
		if(page<=0)
			currentPage=1;
		else if(page>totalPages)
			currentPage=totalPages;
		else
			currentPage=page;
		this.setRowsCount((currentPage-1)*pageSize+1);
	}

	public int getRowsCount() {
		return rowsCount;
	}
	public int getCurrentPageRowsCount() {
		if(pageSize==0)
			return totalRows;
		if(totalRows==0)
			return 0;
		if(currentPage!=totalPages)
			return pageSize;
		return totalRows-(totalPages-1)*pageSize;
	}

	public void setRowsCount(int rowsCount) {
		this.rowsCount = rowsCount;
	}
	public void gotoPage(int page) {
		switch(page) {
		case 1:
			this.setCurrentPage(1);
			break;
		case 2:
			this.setCurrentPage(currentPage-1);
			break;
		case 3:
			this.setCurrentPage(currentPage+1);
			break;
		case 4:
			this.setCurrentPage(totalPages);
			break;
		}
	}
}
